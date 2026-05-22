public class DatabaseService {

    private final Connection connection;

    public DatabaseService(String dbPath) {
        // 1. Abrir conexión JDBC
        connection = DriverManager.getConnection("jdbc:h2:" + dbPath, "sa", "");
        // 2. Ejecutar Liquibase (crea tablas si no existen)
        runLiquibase();
    }

    private void runLiquibase() throws Exception {
        Database db = DatabaseFactory.getInstance()
            .findCorrectDatabaseImplementation(new JdbcConnection(connection));
        new Liquibase(
            "db/changelog/db.changelog-master.xml",
            new ClassLoaderResourceAccessor(), db
        ).update(new Contexts());
    }

    // Mismos nombres que CsvWriter para no cambiar el código de las zonas
    public void appendRevenue(RevenueRecord r) {
        // INSERT INTO revenues (type, amount, tourist_id, zone, timestamp) VALUES (?,?,?,?,?)
        // Usar PreparedStatement — NUNCA concatenar SQL con strings
    }
    public void appendExpense(ExpenseRecord e) { ... }
    public void appendEvent  (EventRecord  ev) { ... }

    public void close() { /* cerrar la conexión */ }
}