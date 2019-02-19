package cn.mariojd.jpa.naming.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;


/**
 * @author Jared
 * @date 2018/12/10 9:50
 */
public class CustomNamingStrategyConfig extends SpringPhysicalNamingStrategy {

    /**
     * 配置映射的数据表名
     *
     * @param name
     * @param jdbcEnvironment
     * @return
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name == null) {
            return null;
        }
        // 实体名 or 自定义的@Tabel name属性值
        String text = name.getText();

        // 首字母大写(类名)，实体未定义@Table, 为表名加上tb_前缀
        if (Character.isUpperCase(text.charAt(0))) {
            final String tb = "tb_";
            if (!text.contains(tb)) {
                text = "tb" + text;
            }

            StringBuilder builder = new StringBuilder(text.replace('.', '_'));
            for (int i = 1, maxLength = builder.length() - 1; i < maxLength; i++) {
                if (this.isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
                    builder.insert(i++, '_');
                }
            }
            return super.getIdentifier(builder.toString(), name.isQuoted(), jdbcEnvironment);
        } else {
            // 实体定义了@Table(name="xxx")，以name属性值为表名
            return super.getIdentifier(text, name.isQuoted(), jdbcEnvironment);
        }
    }

    /**
     * 判断是否前一个字符为小写字母，当前字符为大写字母，下一个字符为小写字母
     *
     * @param before
     * @param current
     * @param after
     * @return
     */
    private boolean isUnderscoreRequired(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
    }

    /**
     * 配置映射的字段名
     *
     * @param name
     * @param jdbcEnvironment
     * @return
     */
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        // 实体的属性名 or 自定义的@Column name属性值
        String text = name.getText();

        if (Character.isUpperCase(text.charAt(0))) {
            // 大写字母下划线分隔命名策略，有在实体字段上自定义@Column(name="Xx_Xxx")
            return new Identifier(text, name.isQuoted());
        } else {
            // 常见的小写驼峰式命名策略
            return super.toPhysicalColumnName(name, jdbcEnvironment);
        }
    }

}
