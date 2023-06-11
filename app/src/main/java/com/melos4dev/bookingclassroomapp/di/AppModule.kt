package com.melos4dev.bookingclassroomapp.di

import com.melos4dev.bookingclassroomapp.core.Constants.BOOKINGS
import com.melos4dev.bookingclassroomapp.core.Constants.CLASSROOMS
import com.melos4dev.bookingclassroomapp.core.Constants.INCIDENCES
import com.melos4dev.bookingclassroomapp.core.Constants.NOTIFICATIONS
import com.melos4dev.bookingclassroomapp.core.Constants.USERS
import com.melos4dev.bookingclassroomapp.data.repository.AuthRepositoryImpl
import com.melos4dev.bookingclassroomapp.data.repository.BookingsRepositoryImpl
import com.melos4dev.bookingclassroomapp.data.repository.ClassroomsRepositoryImpl
import com.melos4dev.bookingclassroomapp.data.repository.IncidencesRepositoryImpl
import com.melos4dev.bookingclassroomapp.data.repository.NotificationsRepositoryImpl
import com.melos4dev.bookingclassroomapp.data.repository.UsersRepositoryImpl
import com.melos4dev.bookingclassroomapp.domain.repository.AuthRepository
import com.melos4dev.bookingclassroomapp.domain.repository.BookingsRepository
import com.melos4dev.bookingclassroomapp.domain.repository.ClassroomsRepository
import com.melos4dev.bookingclassroomapp.domain.repository.IncidencesRepository
import com.melos4dev.bookingclassroomapp.domain.repository.NotificationsRepository
import com.melos4dev.bookingclassroomapp.domain.repository.UsersRepository
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.AuthUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.GetCurrentUser
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Login
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Logout
import com.melos4dev.bookingclassroomapp.domain.use_cases.auth.Signup
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.BookingsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.CreateBooking
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.DeleteBooking
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.GetBookings
import com.melos4dev.bookingclassroomapp.domain.use_cases.bookings.GetBookingsByEmailUser
import com.melos4dev.bookingclassroomapp.domain.use_cases.classrooms.ClassroomsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.classrooms.GetClassrooms
import com.melos4dev.bookingclassroomapp.domain.use_cases.incidences.CreateIncidence
import com.melos4dev.bookingclassroomapp.domain.use_cases.incidences.GetIncidencesByEmailUser
import com.melos4dev.bookingclassroomapp.domain.use_cases.incidences.IncidencesUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.notifications.GetNotifications
import com.melos4dev.bookingclassroomapp.domain.use_cases.notifications.NotificationsUseCases
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.Create
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.GetUserById
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.SaveImage
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.Update
import com.melos4dev.bookingclassroomapp.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import com.melos4dev.bookingclassroomapp.domain.use_cases.incidences.DeleteIncidence

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Named(USERS)
    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            USERS
        )

    @Named(USERS)
    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Named(INCIDENCES)
    @Provides
    fun  provideStorageIncidencesRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(
            INCIDENCES
        )

    @Named(INCIDENCES)
    @Provides
    fun provideIncidencesRef(db: FirebaseFirestore): CollectionReference = db.collection(INCIDENCES)

    @Named(NOTIFICATIONS)
    @Provides
    fun provideNotificationsRef(db: FirebaseFirestore): CollectionReference = db.collection(NOTIFICATIONS)

    @Named(BOOKINGS)
    @Provides
    fun provideBookingsRef(db: FirebaseFirestore): CollectionReference = db.collection(BOOKINGS)

    @Named(CLASSROOMS)
    @Provides
    fun provideClassroomsRef(db: FirebaseFirestore): CollectionReference = db.collection(CLASSROOMS)

    //REPOSITORIES

    @Provides
    fun provideClassroomsRepository(impl: ClassroomsRepositoryImpl): ClassroomsRepository = impl
    @Provides
    fun provideBookingsRepository(impl: BookingsRepositoryImpl): BookingsRepository = impl

    @Provides
    fun provideNotificationsRepository(impl: NotificationsRepositoryImpl): NotificationsRepository = impl
    @Provides
    fun provideIncidencesRepository(impl: IncidencesRepositoryImpl): IncidencesRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    //USE CASES

    @Provides
    fun provideBookingsUseCases(repository: BookingsRepository) = BookingsUseCases(
        create = CreateBooking(repository),
        getBookingsByEmailUser = GetBookingsByEmailUser(repository),
        getBookings = GetBookings(repository),
        deleteBooking = DeleteBooking(repository)
    )
    @Provides
    fun provideNotificationsUseCases(repository:NotificationsRepository) = NotificationsUseCases(
        getNotifications = GetNotifications(repository)
    )

    @Provides
    fun provideIncidencesUseCases(repository: IncidencesRepository) = IncidencesUseCases(
        create = CreateIncidence(repository),
        getIncidencesByEmailUser = GetIncidencesByEmailUser(repository),
        deleteIncidence = DeleteIncidence(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    @Provides
    fun provideClassroomsUseCases(repository:ClassroomsRepository) = ClassroomsUseCases(
        getClassrooms = GetClassrooms(repository)
    )


}