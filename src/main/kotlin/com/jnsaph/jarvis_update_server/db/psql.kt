package com.jnsaph.jarvis_update_server.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet


class PostgreSQL(
    private val url: String,
    private val username: String,
    private val password: String
) {

    private var connection: Connection? = null

    init {
        try {
            Class.forName("org.postgresql.Driver")
        } catch (e: ClassNotFoundException) {
            println("PostgreSQL JDBC Driver not found")
            throw RuntimeException("PostgreSQL JDBC driver not found")
        }
    }
    fun connect() {
        println("Connecting to PostgreSQL database...")
        connection = DriverManager.getConnection(url, username, password)
    }

    fun disconnect() {
        println("Disconnecting from PostgreSQL database...")
        connection?.close()
    }

    fun executeQuery(query: String): ResultSet? {
        val statement = connection?.prepareStatement(query)
        return statement?.executeQuery()
    }

    fun executeUpdate(query: String): Int {
        val statement = connection?.prepareStatement(query)
        return statement?.executeUpdate() ?: -1
    }


}