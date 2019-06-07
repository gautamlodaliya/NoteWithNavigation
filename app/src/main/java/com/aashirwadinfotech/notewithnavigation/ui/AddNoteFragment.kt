package com.aashirwadinfotech.notewithnavigation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.aashirwadinfotech.notewithnavigation.R
import com.aashirwadinfotech.notewithnavigation.database.ui.Note
import com.aashirwadinfotech.notewithnavigation.database.ui.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_done.setOnClickListener {
            val noteTitle = et_title.text.toString().trim()
            val noteBody = et_note.text.toString().trim()

            if (noteTitle.isEmpty()) {
                et_title.error = "Title required"
                et_title.requestFocus()
                return@setOnClickListener
            }

            if (noteBody.isEmpty()) {
                et_note.error = "Note required"
                et_note.requestFocus()
                return@setOnClickListener
            }
            launch {
                val note = Note(noteTitle, noteBody)
                context?.let {
                    NoteDatabase(it).getNoteDao().addNote(note)
                    it.toast("Note Saved")
                }
            }
//            saveNote(note)
        }
    }

//    private fun saveNote(note: Note) {
//        class saveNote : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void?): Void? {
//                NoteDatabase(activity!!).getNoteDao().addNote(note)
//                return null
//            }
//
//            override fun onPostExecute(result: Void?) {
//                super.onPostExecute(result)
//                Toast.makeText(activity, "note saved", Toast.LENGTH_LONG).show()
//            }
//        }
//        saveNote().execute()
//    }

}
