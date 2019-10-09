package com.wiedii.wiicandy.Helpers

class Compra(usuario: String, producto: String, cantidad: String, fecha: String, total: String) {
    var total: String? = null
    var usuario: String? = null
    var producto: String? = null
    var cantidad: String? = null
    var fecha: String? = null

    init {
        this.usuario = usuario
        this.producto = producto
        this.cantidad = cantidad
        this.fecha = fecha
        this.total = total
    }
}