# Daily To-Do List Application

## Pendahuluan
Aplikasi Daily To-Do List ini adalah program berbasis Java Swing yang memungkinkan pengguna untuk mengelola daftar tugas harian mereka. Aplikasi ini mendukung penambahan, pengeditan, penghapusan, pencarian tugas, dan penyimpanan atau pemuatan daftar tugas dari file. Selain itu, aplikasi ini memungkinkan pengguna menambahkan gambar terkait tugas.

---

## Cara Kerja Program
Program ini terdiri dari beberapa komponen utama:
1. **JFrame dan JPanel**: Digunakan untuk membuat antarmuka grafis.
2. **JTable**: Digunakan untuk menampilkan daftar tugas.
3. **JTextField**: Digunakan untuk memasukkan tugas baru.
4. **JFileChooser**: Memungkinkan pengguna memilih file gambar untuk ditambahkan ke tugas.
5. **ArrayList**: Menyimpan data tugas sementara.
6. **ObjectInputStream dan ObjectOutputStream**: Untuk menyimpan dan memuat daftar tugas ke/dari file.

---

## Fitur Utama
1. **Tambah Tugas**: Menambahkan tugas baru ke daftar.
2. **Perbarui Tugas**: Mengedit tugas yang sudah ada.
3. **Hapus Tugas**: Menghapus tugas dari daftar.
4. **Cari Tugas**: Mencari tugas berdasarkan nama.
5. **Tambah Gambar**: Menambahkan gambar untuk tugas.
6. **Simpan Tugas**: Menyimpan daftar tugas ke file.
7. **Muat Tugas**: Memuat daftar tugas dari file.

---

## Penjelasan Fungsi

### 1. `addTask()`
- **Deskripsi**: Menambahkan tugas baru ke tabel dan daftar tugas.
- **Cara Kerja**:
    - Mengambil teks dari `taskField`.
    - Menambahkan teks dan jalur gambar (jika ada) ke `tasks` dan `tableModel`.
    - Mereset kolom input setelah tugas ditambahkan.

### 2. `updateTask()`
- **Deskripsi**: Memperbarui tugas yang dipilih di tabel.
- **Cara Kerja**:
    - Memeriksa apakah baris di tabel dipilih.
    - Mengambil teks dan jalur gambar baru dari input.
    - Memperbarui data di `tasks` dan `tableModel`.

### 3. `deleteTask()`
- **Deskripsi**: Menghapus tugas yang dipilih dari tabel.
- **Cara Kerja**:
    - Memeriksa apakah baris di tabel dipilih.
    - Menghapus tugas dari `tasks` dan `tableModel`.

### 4. `searchTask()`
- **Deskripsi**: Mencari tugas berdasarkan teks yang dimasukkan.
- **Cara Kerja**:
    - Membandingkan teks di `taskField` dengan setiap tugas di `tasks`.
    - Menyorot baris tabel jika ditemukan.

### 5. `chooseImage()`
- **Deskripsi**: Memilih file gambar untuk tugas.
- **Cara Kerja**:
    - Membuka dialog file dengan `JFileChooser`.
    - Menampilkan gambar yang dipilih di `imageLabel`.

### 6. `saveTasksToFile()`
- **Deskripsi**: Menyimpan daftar tugas ke file `tasks.dat`.
- **Cara Kerja**:
    - Menggunakan `ObjectOutputStream` untuk menulis data `tasks` ke file.

### 7. `loadTasksFromFile()`
- **Deskripsi**: Memuat daftar tugas dari file `tasks.dat`.
- **Cara Kerja**:
    - Menggunakan `ObjectInputStream` untuk membaca data dari file.
    - Memperbarui tabel dengan data yang dimuat.

### 8. `resetInputFields()`
- **Deskripsi**: Mereset kolom input setelah tugas ditambahkan, diperbarui, atau dihapus.
- **Cara Kerja**:
    - Mengosongkan `taskField` dan mengatur ulang `imageLabel`.

---

## Panduan Penggunaan
1. Jalankan program dengan menjalankan metode `main` di `DailyToDoListApp`.
2. Gunakan kolom teks untuk memasukkan tugas baru.
3. Klik **Add Task** untuk menambahkan tugas ke daftar.
4. Pilih tugas di tabel dan gunakan tombol **Update Task** untuk memperbarui, atau **Delete Task** untuk menghapusnya.
5. Gunakan tombol **Search Task** untuk mencari tugas berdasarkan nama.
6. Klik **Add Image** untuk menambahkan gambar ke tugas.
7. Klik **Save Tasks** untuk menyimpan daftar ke file.
8. Klik **Load Tasks** untuk memuat daftar dari file.

