package com.example.notekeeper

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.initializeNotes()
    }

    @Test
    fun addNote() {
        val course = DataManager.courses.get("android_async")!!
        val noteTitle = "This is a test note"
        val noteText = "This is the body of my  test note"

        val index = DataManager.addNote(course,noteTitle,noteText)
        val note = DataManager.notes[index]

        assertEquals(course,note.course)
        assertEquals(note.title,note.title)
        assertEquals(noteText,note.text)
    }

    @Test
    fun findSimilarNotes(){
        val course = DataManager.courses["android_async"]
        val noteTitle = "This is a note test"
        val noteText1 = "This is the body the of my test note"
        val noteText2 = "Ths is theX body of my second test note"

        val index1 = DataManager.addNote(course!!,noteTitle,noteText1)
        val index2 = DataManager.addNote(course,noteTitle,noteText2)

        val note1 :NoteInfo? = DataManager.findNote(course, noteTitle, noteText1)
        val foundIndex1 :Int = DataManager.notes.indexOf(noteText1)
        assertEquals(index1, foundIndex1)

        val note2 :NoteInfo? = DataManager.findNote(course, noteTitle, noteText1)
        val foundIndex2: Int = DataManager.notes.indexOf(noteText2)
        assertEquals(index2, foundIndex2)


    }

}