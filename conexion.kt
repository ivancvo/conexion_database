 import java.sql.*

enum class Estado {
    DISPONIBLE, AGOTADO
}

data class Producto(
    val id: Int,
    val nombre: String,
    val marca: String,
    val medida: String,
    val precio_bruto: Double,
    val precio_neto: Double,
    val iva: Double,
    val unidades_totales: Int,
    val estado: Estado
)

class ConexionBD {

    private val url = "jdbc:mysql://localhost:3306/tienda"
    private val user = "root"
    private val password = ""

    val productos = mutableListOf<Producto>()

    fun ejecutarConexion() {
        try {
            val conn: Connection = DriverManager.getConnection(url, user, password)
            val stmt: Statement = conn.createStatement()
            val rs: ResultSet = stmt.executeQuery("SELECT * FROM productos")

            while (rs.next()) {
                val id = rs.getInt("id")
                val nombre = rs.getString("nombre")
                val marca = rs.getString("marca")
                val medida = rs.getString("medida")
                val precio_bruto = rs.getDouble("precio_bruto")
                val precio_neto = rs.getDouble("precio_neto")
                val iva = rs.getDouble("iva")
                val unidades_totales = rs.getInt("unidades_totales")
                val estado = Estado.valueOf(rs.getString("estado").uppercase()) //lo pasa de string a enum

                val producto = Producto(id, nombre, marca, medida, precio_bruto, precio_neto, iva, unidades_totales, estado)
                productos.add(producto)
            }

            println("Conexión exitosa y datos cargados:")
            productos.forEach { println(it) }

            conn.close()
        } catch (e: SQLException) {
            println("Error en la conexión:")
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            println("Error: El campo estado tiene un valor inválido en la base de datos.")
        }
    }
}
