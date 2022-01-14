package de.warsteiner.datax.utils.statements;

import java.util.function.Consumer;

public interface StatementQuery<SQLStatementAPI> extends Consumer<SQLStatementAPI> {

    @Override
    void accept(SQLStatementAPI query);
}
