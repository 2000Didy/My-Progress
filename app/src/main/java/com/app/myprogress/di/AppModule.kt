package com.app.myprogress.di

import android.app.Application
import androidx.room.Room
import com.app.myprogress.feature_note.domaine.depot.NoteDepot
import com.app.myprogress.feature_note.domaine.utilisation.*
import com.app.myprogress.feature_note.donnee.depot.NoteDepotImpl
import com.app.myprogress.feature_note.donnee.src_donnee.NoteBdd
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteBdd(app: Application): NoteBdd {
        return Room.databaseBuilder(
            app,
            NoteBdd::class.java,
            NoteBdd.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDepot(db: NoteBdd): NoteDepot {
        return NoteDepotImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUtilisation(depot: NoteDepot): NoteUtilisation {
        return NoteUtilisation(
            getNotes = GetNotes(depot),
            suppNote = SuppNote(depot),
            ajoutNote = AjoutNote(depot),
            getNote = GetNote(depot)
        )
    }
}