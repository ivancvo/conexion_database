package com.example.mavecito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController

class ConexionController {

    @Autowired
    lateinit var conexionService: ConexionService

    @GetMapping("/Productos")

    fun obtenerProductos() : List<Producto> {
        return try {
            conexionService.obtenerProductos()
        } catch (e: Exception){
            println("error al obtener productos: ${e.message}")
            throw RuntimeException("error al obtener productos")
        }
    }
    @GetMapping("/Usuarios")
    fun obtenerUsuarios(): List<Usuario>{
        return try {
            conexionService.obtenerUsuarios()
        } catch (e: Exception){
            println("error al obtener usuarios: ${e.message}")
            throw  RuntimeException("error al obtener usuarios")
        }
    }
}