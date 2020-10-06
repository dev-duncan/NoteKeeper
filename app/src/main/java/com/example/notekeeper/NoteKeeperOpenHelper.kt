package com.example.notekeeper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class NoteKeeperOpenHelper( context: Context?
): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

            db?.execSQL(NoteKeeperDatabaseContract.CourseInfoEntry.SQL_CREATE_TABLE)
            db?.execSQL(NoteKeeperDatabaseContract.NoteInfoEntry.SQL_CREATE_TABLE)


        val worker = DatabaseDataWorker(db)
        worker.insertCourses()
        worker.insertSampleNotes()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}





