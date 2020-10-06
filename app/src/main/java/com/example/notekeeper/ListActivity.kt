package com.example.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class ListActivity : AppCompatActivity() {
    private var notePosition: Int = POSITION_NOT_SET


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        val adapterCourses = ArrayAdapter(this, android.R.layout.simple_spinner_item,DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
          intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

       if(notePosition != POSITION_NOT_SET)
           displayNote()
        else{
           DataManager.notes.add(NoteInfo())
           notePosition = DataManager.notes.lastIndex
       }

    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.noteTitle)
        textNoteText.setText(note.noteText)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.action_next->{
                moveNext()

            }
        }
        return true
    }
    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem =  menu?.findItem(R.id.action_next)
            if (menuItem != null){
                Toast.makeText(this, "No More Notes To Display", Toast.LENGTH_SHORT).show()
                menuItem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        saveNotes()
        super.onPause()

    }

    private fun saveNotes() {
       val note = DataManager.notes[notePosition]
        note.noteTitle = textNoteTitle.text.toString()
        note.noteText = textNoteTitle.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)
    }



}