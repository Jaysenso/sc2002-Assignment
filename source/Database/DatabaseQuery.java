package source.Database;

/**
 * A wrapper class to denote a database query, similar to a pair of an objects
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/21/2023
 */
public final class DatabaseQuery {
    /**
     * The query to find in the database
     */
    private final String query;
    /**
     * The table/header to look into
     */
    private final String header;

    /**
     * An overloaded constructor that initializes the query and header
     *
     * @param query  the query
     * @param header the header
     */
    public DatabaseQuery(String query, String header) {
        this.query = query;
        this.header = header;
    }

    /**
     * Acquires the query
     *
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Acquires the header
     *
     * @return header
     */
    public String getHeader() {
        return header;
    }
}
