package com.example.patient.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.patient.model.AppointmentVO

class DB (context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASENAME, factory, DATABASEVERSION) {

    companion object{
        lateinit var database: SQLiteDatabase

        private val DATABASENAME = "patientApp.db"
        private val DATABASEVERSION = 1

        const val AppointmentTABLENAME = "Appointment"
        const val AppointmentCOLIDTable = 0
        const val AppointmentCOLAPPOINTMENTID = 1
        const val AppointmentCOLCODE = 2

        val AppointmentCOLS: Array<String> = arrayOf<String>("idTable", "appointmentId", "code")
        const val AppointmentNUMBERCOLS = 2
    
   }
private val AppointmentCREATESCHEMA =
    "create table Appointment (idTable integer primary key autoincrement" +
        ", appointmentId VARCHAR(50) not null"+
        ", code VARCHAR(50) not null"+
    ")"

    override fun onCreate(db: SQLiteDatabase) {
         db.execSQL(AppointmentCREATESCHEMA)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + AppointmentCREATESCHEMA)
        onCreate(db)
    }

    fun onDestroy() {
        database.close()
    }

    fun listAppointment(): ArrayList<AppointmentVO> {
        val res = ArrayList<AppointmentVO>()
        database = readableDatabase
        val cursor: Cursor =
            database.query(AppointmentTABLENAME, AppointmentCOLS, null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast()) {
            val appointmentvo = AppointmentVO()
            appointmentvo.setAppointmentId(cursor.getString(AppointmentCOLAPPOINTMENTID))
            appointmentvo.setCode(cursor.getString(AppointmentCOLCODE))
            res.add(appointmentvo)
            cursor.moveToNext()
        }
        cursor.close()
        return res
    }

    fun createAppointment(appointmentvo: AppointmentVO) {
        database = writableDatabase
        val wr = ContentValues(AppointmentNUMBERCOLS)
        wr.put(AppointmentCOLS[AppointmentCOLAPPOINTMENTID], appointmentvo.getAppointmentId())
        wr.put(AppointmentCOLS[AppointmentCOLCODE], appointmentvo.getCode())
        database.insert(AppointmentTABLENAME, AppointmentCOLS[1], wr)
    }

    fun searchByAppointmentappointmentId(value: String): ArrayList<AppointmentVO> {
            val res = ArrayList<AppointmentVO>()
	        database = readableDatabase
	        val args = arrayOf(value)
	        val cursor: Cursor = database.rawQuery(
	            "select idTable, appointmentId, code from Appointment where appointmentId = ?",
	            args
	        )
	        cursor.moveToFirst()
	        while (!cursor.isAfterLast()) {
	            val appointmentvo = AppointmentVO()
	            appointmentvo.setAppointmentId(cursor.getString(AppointmentCOLAPPOINTMENTID))
	            appointmentvo.setCode(cursor.getString(AppointmentCOLCODE))
	            res.add(appointmentvo)
	            cursor.moveToNext()
	        }
	        cursor.close()
	        return res
	    }
	     
    fun searchByAppointmentcode(value: String): ArrayList<AppointmentVO> {
            val res = ArrayList<AppointmentVO>()
	        database = readableDatabase
	        val args = arrayOf(value)
	        val cursor: Cursor = database.rawQuery(
	            "select idTable, appointmentId, code from Appointment where code = ?",
	            args
	        )
	        cursor.moveToFirst()
	        while (!cursor.isAfterLast()) {
	            val appointmentvo = AppointmentVO()
	            appointmentvo.setAppointmentId(cursor.getString(AppointmentCOLAPPOINTMENTID))
	            appointmentvo.setCode(cursor.getString(AppointmentCOLCODE))
	            res.add(appointmentvo)
	            cursor.moveToNext()
	        }
	        cursor.close()
	        return res
	    }
	     

    fun editAppointment(appointmentvo: AppointmentVO) {
        database = writableDatabase
        val wr = ContentValues(AppointmentNUMBERCOLS)
        wr.put(AppointmentCOLS[AppointmentCOLAPPOINTMENTID], appointmentvo.getAppointmentId())
        wr.put(AppointmentCOLS[AppointmentCOLCODE], appointmentvo.getCode())
        val args = arrayOf(appointmentvo.getAppointmentId())
        database.update(AppointmentTABLENAME, wr, "appointmentId =?", args)
    }

    fun deleteAppointment(value: String) {
        database = writableDatabase
        val args = arrayOf(value)
        database.delete(AppointmentTABLENAME, "appointmentId = ?", args)
    }

}
