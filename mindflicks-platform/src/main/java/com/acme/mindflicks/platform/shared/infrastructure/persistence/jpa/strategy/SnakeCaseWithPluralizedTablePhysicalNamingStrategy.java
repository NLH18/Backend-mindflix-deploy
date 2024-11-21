package com.acme.mindflicks.platform.shared.infrastructure.persistence.jpa.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;

public class SnakeCaseWithPluralizedTablePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return null;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }

    /**
     * Convert identifier to snake case
     * @param identifier Identifier
     * @return identifier
     */
    private Identifier toSnakeCase(Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String snakeCase = identifier.getText().replaceAll(regex, replacement).toLowerCase();
        return Identifier.toIdentifier(snakeCase);
    }

    /**
     * Convert identifier to plural
     * @param identifier
     * @return Identifier
     */
    private Identifier toPlural(final Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}
