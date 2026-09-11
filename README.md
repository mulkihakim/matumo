# Matumo

Matumo adalah aplikasi Android sederhana untuk membantu mengelola tugas harian. Proyek ini dibuat sebagai bagian dari proses menyelesaikan sertifikasi mobile development sekaligus sebagai sarana belajar dan mengeksplorasi teknologi pengembangan aplikasi mobile dengan Kotlin.

## Fitur

- Login lokal untuk demo aplikasi
- Menambahkan tugas berdasarkan kategori
- Menampilkan daftar tugas
- Mengubah status tugas menjadi selesai
- Menghapus dan memperbarui tugas
- Menampilkan statistik tugas
- Mengubah password lokal
- Menyimpan data menggunakan database lokal

## Teknologi

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room Database
- ViewModel
- Repository pattern
- Kotlin Coroutines dan Flow
- Gradle Kotlin DSL

## Arsitektur Sederhana

Kode aplikasi dipisahkan berdasarkan tanggung jawab:

```text
app/src/main/java/com/mulki/matumo/
├── components/       Komponen UI yang dapat digunakan kembali
├── data/local/       Entity, DAO, database, dan preference lokal
├── data/repository/  Penghubung antara sumber data dan ViewModel
├── navigation/       Konfigurasi navigasi aplikasi
├── screens/          Layar-layar utama aplikasi
├── ui/theme/         Warna, typography, dan tema Compose
└── viewmodel/        State dan operasi data untuk UI
```

Struktur dan beberapa penamaan folder dirancang dengan bantuan ChatGPT sebagai bagian dari proses belajar. Implementasi, penyesuaian, dan keputusan akhir tetap ditinjau dan dipahami oleh pemilik proyek.

## Menjalankan Proyek

Prasyarat:

- Android Studio
- JDK yang kompatibel dengan Gradle project
- Android SDK 34
- Emulator atau perangkat Android dengan minimum Android 7.0 (API 24)

Langkah:

1. Clone repository ini.
2. Buka folder project di Android Studio.
3. Tunggu proses Gradle sync selesai.
4. Jalankan aplikasi pada emulator atau perangkat Android.

Untuk memeriksa project melalui terminal Windows:

```powershell
.\gradlew.bat test
.\gradlew.bat :app:assembleDebug
```

## Akun Demo

```text
Username: user
Password awal: user
```

Password dapat diubah melalui menu Pengaturan.

## Batasan Demo

Login, password, dan data tugas disimpan secara lokal pada perangkat. Aplikasi ini tidak menggunakan backend, akun online, atau autentikasi produksi. Password juga belum menggunakan sistem penyimpanan aman untuk aplikasi produksi.

Karena itu, Matumo dipublikasikan sebagai proyek pembelajaran dan portofolio, bukan sebagai contoh implementasi keamanan untuk aplikasi production.

## Pengujian

Project memiliki unit test sederhana untuk default state entity dan penerusan operasi CRUD dari Repository ke DAO fake. Pengujian instrumented dapat dijalankan pada emulator atau perangkat Android jika tersedia.

## Konteks Pembelajaran

Dalam proses pengembangan Matumo, ChatGPT digunakan sebagai pendamping belajar untuk:

- Memahami konsep Kotlin dan Android.
- Mendiskusikan pola arsitektur aplikasi.
- Mendapatkan saran penamaan folder dan pemisahan tanggung jawab kode.
- Membantu membaca error dan menyusun test sederhana.

Penggunaan bantuan AI menjadi bagian dari proses eksplorasi teknologi, sedangkan kode tetap dipelajari, diuji, dan disesuaikan dengan kebutuhan proyek.

## Status Repository

Repository ini ditujukan untuk menampilkan hasil belajar dan proses pengembangan pribadi. Foto pribadi dan data yang tidak diperlukan untuk demonstrasi aplikasi tidak disertakan.
