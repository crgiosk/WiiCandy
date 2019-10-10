package com.wiedii.wiicandy.Helpers

class Compra(usuario: String, producto: String, cantidad: String, fecha: String, total: String,icono:Int) {
    var total: String? = null
    var usuario: String? = null
    var producto: String? = null
    var cantidad: String? = null
    var fecha: String? = null
    var icono:Int? = null

    init {
        this.usuario = usuario
        this.producto = producto
        this.cantidad = cantidad
        this.fecha = fecha
        this.total = total
        this.icono= icono
    }
}