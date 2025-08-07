# Project Recycler view

Ini adalah proyek sederhana untuk belajar Recycler View

## 👥 Tim
- Liana Amaliyatussyarifa 
- Nur Aimah Auliya Aprilianingrum
- Pramudya Kalya Zaki

## 📱 Fitur
- Tambah data
- Hapus data
- Edit data
- Lihat data
  
## 🔧 Teknologi
- Kotlin
- Android Studio
- Git + GitHub

## 📸 Penjelasan code penting
### StudentAdapter.kt
1. Deklarasi dan Konstruktor
class StudentAdapter(
    private val context: Context,
    private val studentList: MutableList<Student>
)
Adapter menerima dua parameter:
context: digunakan untuk membuat dialog dan akses resource Android
studentList: daftar siswa yang akan ditampilkan

private var filteredList = studentList.toMutableList()
Variabel ini digunakan untuk menyimpan data yang sedang ditampilkan, termasuk saat dilakukan pencarian/filter.

2. Fungsi removeItem(position)
Menghapus siswa dari daftar berdasarkan posisi item di RecyclerView.
Data di dua list (studentList dan filteredList) akan disinkronkan agar konsisten.

3. Fungsi editItem(position, updatedStudent)
Mengganti data siswa dengan data baru (updatedStudent) di posisi tertentu.

4. Fungsi refreshData()
Digunakan untuk me-refresh tampilan RecyclerView, biasanya setelah data berubah.
Menyalin ulang data dari studentList ke filteredList.

5. Fungsi showEditDialog(position)
- Menampilkan dialog (popup) untuk mengedit data siswa.
- Isi form diambil dari data siswa saat ini
- Setelah diedit, data akan diperbarui
- Menampilkan Toast jika berhasil

6. Inner Class ViewHolder
inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
Mewakili satu item tampilan siswa dalam daftar RecyclerView.
Menampung:
Nama siswa (tvNama)
NIS (tvNis)
Kelas (tvKelas)
Tombol Edit dan Hapus

7. Fungsi onCreateViewHolder()
Membuat tampilan baru (inflate) untuk satu item siswa dari layout item_student.xml.

8. Fungsi getItemCount()
Mengembalikan jumlah item siswa yang sedang ditampilkan (filteredList).

9. Fungsi onBindViewHolder()
Mengisi data ke tampilan berdasarkan posisi:
Menampilkan nama, NIS, dan kelas siswa
Mengatur aksi untuk tombol Edit dan Hapus
Edit: memanggil showEditDialog()
Hapus: konfirmasi lalu menghapus siswa dari daftar

10. Fungsi getFilter()
Menyediakan fitur pencarian (searching) pada daftar siswa.
performFiltering: menyaring data berdasarkan teks yang diketik user (nama, NIS, kelas)
publishResults: menampilkan hasil filter ke RecyclerView

### Student.kt
Student.kt adalah class model berbentuk data class yang digunakan untuk merepresentasikan data siswa dalam aplikasi. Class ini berada dalam package com.smkth.app3_recycleview.model dan memiliki tiga properti utama:
nama: menyimpan nama siswa (String)
nis: menyimpan Nomor Induk Siswa (String)
kelas: menyimpan informasi kelas siswa (String)
Sebagai data class, Student otomatis memiliki fungsi toString(), equals(), hashCode(), dan copy(), yang sangat berguna untuk pengolahan data di RecyclerView dan proses filtering, editing, maupun penghapusan data siswa.

### DummyData.kt
fun getStudentList(): List<Student>
Fungsi ini mengembalikan list dari Student, yaitu model data siswa yang berisi:
nama: nama siswa
nis: Nomor Induk Siswa
kelas: kelas siswa

🧾 Contoh Data yang Diberikan
Beberapa data siswa dummy yang dimasukkan:
Pram – 191919 – XII TKJ 1
Nopal – 303030 – XII TKJ 1
Roy – 606060 – XII TKJ 4
dan lainnya...
Data ini berguna untuk mengisi RecyclerView secara otomatis saat aplikasi pertama kali dijalankan, terutama saat belum terhubung ke database.

### DetailActivity.kt
private lateinit var tvNama: TextView
private lateinit var tvNis: TextView
private lateinit var tvKelas: TextView
Tiga TextView ini digunakan untuk menampilkan informasi siswa.

🧠 Fungsi onCreate()
Fungsi utama yang dijalankan saat Activity dimulai.
setContentView() menampilkan layout activity_detail.xml.
ViewCompat.setOnApplyWindowInsetsListener() digunakan untuk menyesuaikan padding tampilan agar tidak tertutup sistem bar (fitur UI responsif).
findViewById() menghubungkan TextView dari layout ke variabel Kotlin.
intent.getStringExtra() mengambil data dari Intent yang dikirim dari Activity sebelumnya (misalnya saat klik item siswa).
Menampilkan data siswa ke TextView.

📥 Data yang Diterima via Intent
student_nama
student_nis
student_kelas
Ketiga data tersebut dikirim dari activity sebelumnya (biasanya MainActivity atau tempat di mana RecyclerView berada) saat siswa diklik.

### MainActivity.kt
1. RecyclerView dan StudentAdapter
recyclerView = findViewById(R.id.recyclerView)
adapter = StudentAdapter(this, studentList)
Digunakan untuk menampilkan daftar siswa dalam bentuk list menggunakan adapter StudentAdapter.
Layout-nya diatur dengan LinearLayoutManager.

2. studentList
private val studentList = DummyData.getStudentList().toMutableList()
Data siswa diambil dari DummyData, kemudian disimpan dalam list yang bisa diubah (mutable).

3. Pencarian dengan SearchView
searchView.setOnQueryTextListener(...)
Saat user mengetik di SearchView, adapter akan memfilter data berdasarkan nama, NIS, atau kelas menggunakan method filter.filter(newText).

4. Tambah Siswa dengan Dialog
btnTambah.setOnClickListener {
    showAddStudentDialog()
}
Memanggil fungsi showAddStudentDialog() yang menampilkan AlertDialog dengan input nama, NIS, dan kelas siswa.
Setelah dikonfirmasi, data siswa ditambahkan ke studentList, lalu adapter di-refresh agar data tampil di RecyclerView.

5. UI Responsif dengan WindowInsets
ViewCompat.setOnApplyWindowInsetsListener(...)
Digunakan untuk menyesuaikan padding pada layout agar tidak tertutup status bar atau sistem bar, demi kenyamanan tampilan.

### activity_detail.xml
1. Root Layout: LinearLayout
<LinearLayout
    android:id="@+id/main"
    android:orientation="vertical"
    android:padding="20dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
Ini adalah layout utama yang menyusun semua elemen secara vertikal (atas ke bawah).
android:padding="20dp" memberi jarak di seluruh sisi.
@+id/main penting karena dipakai oleh ViewCompat.setOnApplyWindowInsetsListener() di DetailActivity.kt.

2. Judul Tampilan
<TextView
    android:text="Biodata Siswa"
    android:gravity="center"
    android:textSize="40sp"
    android:textColor="@color/black"
    android:textStyle="bold"
Menampilkan teks besar “Biodata Siswa” di tengah layar.
textSize="40sp" membuat teks terlihat besar.
textColor="@color/black" mengatur warna teks (pastikan black tersedia di res/values/colors.xml).
gravity="center" agar teksnya rata tengah.

3. Gambar Siswa
<ImageView
    android:layout_width="80dp"
    android:layout_height="80dp"
    android:layout_gravity="center"
    android:src="@drawable/fotosiswa"
Menampilkan ikon siswa dari file drawable bernama fotosiswa.png/jpg.
Ukuran gambar 80dp x 80dp.
Diletakkan di tengah (layout_gravity="center").

4. TextView tvNama
<TextView
    android:id="@+id/tvNama"
    android:layout_gravity="center"
    android:textSize="20sp"
    android:textStyle="bold"
Menampilkan nama siswa.
Akan diisi oleh kode Kotlin tvNama.text = nama di DetailActivity.kt.

5. TextView tvNis
<TextView
    android:id="@+id/tvNis"
    android:layout_gravity="center"
    android:textSize="18sp"
Menampilkan NIS siswa.
Juga akan diisi dari data Intent.

6. TextView tvKelas
<TextView
    android:id="@+id/tvKelas"
    android:layout_gravity="center"
    android:textSize="18sp"
Menampilkan kelas siswa.
Juga akan diisi dari Intent.

🔁 Koneksi ke DetailActivity.kt
val nama = intent.getStringExtra("student_nama")
val nis = intent.getStringExtra("student_nis")
val kelas = intent.getStringExtra("student_kelas")

tvNama.text = nama
tvNis.text = nis
tvKelas.text = kelas
Artinya:

DetailActivity menerima data dari MainActivity (melalui Intent).
Data tersebut lalu diatur ke TextView berdasarkan ID masing-masing (tvNama, tvNis, tvKelas).

🧪 Catatan Penting
Pastikan:
fotosiswa.png ada di folder res/drawable
Warna @color/black terdefinisi di colors.xml
Layout ini hanya menampilkan data, tidak bisa mengedit.
@+id/main dipakai untuk kompatibilitas dengan WindowInsets agar tidak tertutup status bar.

### activity_main.xml
1. Button Tambah Siswa
<Button
    android:id="@+id/btnTambah"
    android:text="Tambah Siswa"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintStart_toStartOf="parent" />
Tombol ini akan dipakai untuk menambah data siswa.
Letaknya di pojok kiri atas layar (Top_toTop, Start_toStart).
Nanti bisa dihubungkan dengan btnTambah.setOnClickListener { ... } di MainActivity.kt.

2. SearchView (Pencarian)
<SearchView
    android:id="@+id/searchView"
    android:queryHint="Cari siswa..."
    app:layout_constraintTop_toBottomOf="@id/btnTambah"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
Ini kolom pencarian siswa.
Terletak di bawah tombol tambah.
queryHint adalah teks placeholder: "Cari siswa..."
Nanti bisa digunakan untuk menyaring isi RecyclerView secara real-time.

3. RecyclerView (Daftar Siswa)
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    app:layout_constraintTop_toBottomOf="@id/searchView"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
Ini adalah daftar data siswa yang akan ditampilkan menggunakan RecyclerView.
Letaknya mengisi sisa ruang di bawah SearchView hingga ke bawah layar.
RecyclerView akan dihubungkan ke Adapter yang menampilkan data Student.

### dialog_edit__student.xml
<LinearLayout ... android:orientation="vertical">
LinearLayout vertikal: setiap elemen ditumpuk dari atas ke bawah.
padding="20dp" memberi ruang di sekeliling konten agar tidak terlalu mepet dengan pinggir layar.

📝 Komponen Input
1. EditText untuk Nama Siswa
<EditText
    android:id="@+id/edtNama"
    android:hint="Nama Siswa"
    android:inputType="textPersonName" />
Untuk memasukkan nama lengkap siswa
hint akan tampil saat kosong sebagai petunjuk input
inputType="textPersonName" menyesuaikan keyboard untuk mengetik nama

2. EditText untuk NIS
<EditText
    android:id="@+id/edtNis"
    android:hint="NIS"
    android:inputType="number" />
Untuk menginput Nomor Induk Siswa
inputType="number" akan memunculkan keyboard angka

3. EditText untuk Kelas
<EditText
    android:id="@+id/edtKelas"
    android:hint="Kelas"
    android:inputType="text" />
Untuk menginput kelas siswa (misal: X RPL 1)

### item_student.xml
📦 CardView
<androidx.cardview.widget.CardView
    android:layout_margin="8dp"
    card_view:cardCornerRadius="8dp"
    card_view:cardElevation="4dp">
CardView digunakan untuk membuat tampilan seperti kartu.
cardCornerRadius="8dp" membuat sudut kartu melengkung.
cardElevation="4dp" memberi efek bayangan agar terlihat menonjol.

👤 Bagian Data Siswa
Berada di dalam LinearLayout horizontal, berisi:
Foto siswa
Nama, NIS, dan Kelas
<ImageView
    android:src="@drawable/fotosiswa"
/>
<TextView android:id="@+id/tvNama" />
<TextView android:id="@+id/tvNis" />
<TextView android:id="@+id/tvKelas" />
ImageView: Menampilkan ikon atau foto siswa (@drawable/fotosiswa)
TextView tvNama: Menampilkan nama siswa
TextView tvNis: Menampilkan NIS siswa
TextView tvKelas: Menampilkan kelas siswa
Semua teks ini akan diganti secara dinamis melalui adapter di RecyclerView berdasarkan data siswa.

🛠 Tombol Aksi
<Button android:id="@+id/btnEdit" />
<Button android:id="@+id/btnHapus" />
Tombol ini digunakan untuk mengedit atau menghapus data siswa.

## 📸 Screenshot
StudentAdapter.kt

<img width="1366" height="730" alt="1" src="https://github.com/user-attachments/assets/fd26eddc-792d-455b-89ba-380fd8b8577d" />
<img width="1366" height="680" alt="2" src="https://github.com/user-attachments/assets/1a5b7144-6a81-403e-9433-fc092f0f6473" />
<img width="1366" height="678" alt="3" src="https://github.com/user-attachments/assets/18a3b8cb-ceeb-43e7-b308-f679508cdeb5" />
<img width="1366" height="667" alt="4" src="https://github.com/user-attachments/assets/8e385f98-5cc7-49ed-8e57-c4ad8df235b0" />
<img width="1366" height="705" alt="5" src="https://github.com/user-attachments/assets/1611c99a-c082-4dc3-9e10-ab43049a95c3" />
<img width="968" height="171" alt="6" src="https://github.com/user-attachments/assets/bea6b146-c653-4f4f-a2d0-c70c9a164723" />

Student.kt

<img width="427" height="224" alt="student kt" src="https://github.com/user-attachments/assets/ce053474-31da-4ee1-820c-0f31a99d90e0" />

DummyData.kt

<img width="715" height="470" alt="dummy data" src="https://github.com/user-attachments/assets/bb955c01-d601-4ca8-aec8-794f69e1d7a7" />

DetailActivity.kt

<img width="980" height="627" alt="detail activity" src="https://github.com/user-attachments/assets/f09ff670-dd56-4ad0-a0e7-7007f8780f16" />

MainActivity.kt

<img width="979" height="623" alt="main acti  1" src="https://github.com/user-attachments/assets/4a24ef45-cd00-414e-9d26-eb363942db3c" />
<img width="965" height="618" alt="main acti 2" src="https://github.com/user-attachments/assets/1612cbc3-561a-4f8e-b3c6-81199c2c0e05" />

activity_detail.xml

<img width="975" height="614" alt="ac detail 1" src="https://github.com/user-attachments/assets/d4fdc031-4ff4-4c98-b08e-b4b37a9ceb3f" />
<img width="979" height="486" alt="ac detail 2" src="https://github.com/user-attachments/assets/3b21d405-3889-4689-b0a8-a2e744f750a8" />

activity_main.xml

<img width="978" height="416" alt="ac main 1" src="https://github.com/user-attachments/assets/1d013bdd-fc13-45dd-a368-678f1b87302f" />
<img width="976" height="528" alt="ac main 2" src="https://github.com/user-attachments/assets/9b520e5d-ecca-4a2c-8e75-f6dcf1f91e46" />

dialog_edit__student.xml

<img width="973" height="624" alt="dialog 1" src="https://github.com/user-attachments/assets/8da488ed-c8a4-4205-9d53-099b952e1868" />

item_student.xml

<img width="992" height="621" alt="item 1" src="https://github.com/user-attachments/assets/e26a3a52-0ae9-4a6a-b12c-d4b7765b86a1" />
<img width="975" height="511" alt="item 2" src="https://github.com/user-attachments/assets/5ee48df1-1640-4c83-8d8c-8451424d2ed0" />
<img width="872" height="529" alt="item 3" src="https://github.com/user-attachments/assets/e72aa637-27a7-4428-a99f-307c6cde670f" />
<img width="877" height="228" alt="item 4" src="https://github.com/user-attachments/assets/0774614f-c614-431c-a5f2-8a08784fe6e2" />

