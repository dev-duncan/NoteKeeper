package com.example.notekeeper

import com.example.notekeeper.NoteKeeperDatabaseContract.CourseInfoEntry
import com.example.notekeeper.NoteKeeperDatabaseContract.NoteInfoEntry

object DataManager {
    val courses = HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()


    fun loadFromDataBase(dbHelper: NoteKeeperOpenHelper){
        val db = dbHelper.readableDatabase

        val columnsName = arrayOf(
            CourseInfoEntry.COLUMN_COURSE_ID,
            CourseInfoEntry.COLUMN_COURSE_TITLE
        )
        val courseCursor = db.query(
            CourseInfoEntry.TABLE_NAME,
            columnsName,
            null,
            null,
            null,
            null,
            null
        )

        val noteColumns = arrayOf(
            NoteInfoEntry.COLUMN_NOTE_TITLE,
            NoteInfoEntry.COLUMN_NOTE_TEXT,
            NoteInfoEntry.COLUMN_COURSE_ID
        )
        val NoteCursor = db.query(
            NoteInfoEntry.TABLE_NAME, noteColumns,
            null,
            null,
            null,
            null,
            null
        )

    }

    fun addNote(course: CourseInfo, noteTitle:String, noteText:String): Int {
        val note= NoteInfo(course, noteTitle, noteText)
        notes.add(note)
      return notes.lastIndex

    }

//    fun findNote(course: CourseInfo, noteTitle: String, noteText: String):NoteInfo?{
//        for (note in notes)
//            if (course == note.course &&
//                    noteTitle == note.title &&
//                    noteText == note.text)
//                return note
//            return null
//
//    }

     fun initializeCourses(){
        var course = CourseInfo("android_intents", "Android programming with intents")
        courses.set(course.courseId, course)

        course = CourseInfo("android_async","Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core","java_fundamentals: the core platform ")
        courses.set(course.courseId, course)

    }

    fun initializeNotes() {
        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        notes.add(note)

        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        notes.add(note)
    }


}