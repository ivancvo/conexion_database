package org.example

import ConexionBD
import Modulos.ProductModulelmpl
import controladores.ProductController


fun main() {
    val productModule = ProductModulelmpl()
    val productController = ProductController(productModule)

    /* while (true) {
        println("\n Gestión de Productos \n 1. Agregar Producto\n 2. Listar Productos \n 3. Salir \n Selecciona una opción:")
        when (readln().toIntOrNull()) {
            1 -> productController.registrarProducto()
            2 -> productController.mostrarProductos()
            3 -> {
                println(" Saliendo del sistema...")
                break
                }
            else -> println("Opción inválida, intenta de nuevo.")
        }
    }*/
    val conexion = ConexionBD()
    conexion.ejecutarConexion()

}