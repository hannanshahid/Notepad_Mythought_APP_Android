package com.example.mythought_app.database

class Note_model_class {
    var id=0
    var note:String=""
    var datetime:String=""
    var colour:String=""

    constructor(note: String, datetime: String, colour: String) {
        this.note = note
        this.datetime = datetime
        this.colour = colour
    }
    constructor()
    {

    }
}