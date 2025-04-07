package com.example.mavecito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service
enum class Estado{
    DISPONIBLE, AGOTADO
}
data class Producto(val nombre: String,
                    val marca: String,
                    val medida: String,
                    val precio_bruto :Double,
                    val precio_neto : Double,
                    val iva: Double,
                    val unidades_totales : Int,
                    val estado: Estado)

enum class Rol{
    ADMINISTRADOR, EMPLEADO
}
data class Usuario(val nombre: String,
                   val contraseña: String,
                   val cedula: String,
                   val email : String,
                   val telefono: String,
                   val rol: Rol)


@Service
class ConexionService {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    fun obtenerProductos(): List<Producto> {
        val sql = "SELECT * FROM productos"

        return jdbcTemplate.query(sql) { rs, _ ->
            Producto(
                nombre =  rs.getString("nombre"),
                marca = rs.getString("marca"),
                medida = rs.getString("medida"),
                precio_bruto = rs.getDouble("precio_bruto"),
                precio_neto = rs.getDouble("precio_neto"),
                iva = rs.getDouble("iva"),
                unidades_totales = rs.getInt("unidades_totales"),
                estado = Estado.valueOf(rs.getString("estado").uppercase())
            )
        }
    }
    fun obtenerUsuarios(): List<Usuario>{
        val sql = "SELECT * FROM usuario"
        return  jdbcTemplate.query(sql){rs, _ ->
            Usuario(
                nombre = rs.getString("nombre"),
                contraseña = rs.getString("contraseña"),
                cedula = rs.getString("cedula"),
                email = rs.getString("email"),
                telefono = rs.getString("telefono"),
                rol = Rol.valueOf(rs.getString("rol").uppercase())
            )
        }
    }
}