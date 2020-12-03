-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2020 at 10:57 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(20) NOT NULL,
  `judul` varchar(25) NOT NULL,
  `penerbit` varchar(30) NOT NULL,
  `tahun_terbit` int(4) NOT NULL,
  `kategori` varchar(20) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `penerbit`, `tahun_terbit`, `kategori`, `jumlah`) VALUES
('BK002', 'one piece', 'oda', 1999, 'komik', 14),
('BK003', 'kelinci terbit', 'es teh', 2001, 'novel', 10),
('BK004', 'naruto', 'masashi', 1999, 'komik', 12),
('BK005', 'algoritma', 'james', 1999, 'pendidikan', 10);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `no_pinjam` varchar(20) NOT NULL,
  `NIS` varchar(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `id_buku` varchar(20) NOT NULL,
  `judul` varchar(25) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_hrskembali` date NOT NULL,
  `jumlah` int(1) NOT NULL,
  `keterangan` enum('Sudah Kembali','Belum Kembali') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `peminjaman`
--
DELIMITER $$
CREATE TRIGGER `pinjam_buku` AFTER INSERT ON `peminjaman` FOR EACH ROW BEGIN
update buku SET jumlah = jumlah - new.jumlah
WHERE id_buku = new.id_buku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `no_kembali` varchar(20) NOT NULL,
  `no_pinjam` varchar(20) NOT NULL,
  `NIS` varchar(20) NOT NULL,
  `id_buku` varchar(20) NOT NULL,
  `tgl_kembali` date NOT NULL,
  `denda` int(11) NOT NULL,
  `keterangan` enum('Telat','Tidak Telat','Hilang') NOT NULL,
  `jumlah` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `pengembalian`
--
DELIMITER $$
CREATE TRIGGER `kembali_buku` AFTER INSERT ON `pengembalian` FOR EACH ROW BEGIN
UPDATE buku SET jumlah = jumlah + new.jumlah
WHERE id_buku = new.id_buku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `NIS` varchar(20) NOT NULL,
  `id_user` varchar(20) NOT NULL,
  `nama_siswa` varchar(25) NOT NULL,
  `kelamin` varchar(15) NOT NULL,
  `status` enum('Pinjam','Tidak Pinjam') NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `no_hp` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`NIS`, `id_user`, `nama_siswa`, `kelamin`, `status`, `kelas`, `no_hp`) VALUES
('1755201304', 'USR003', 'ling', 'Laki-Laki', 'Tidak Pinjam', '5', '9009808977897'),
('1755201318', 'USR004', 'neva', 'Laki-Laki', 'Tidak Pinjam', '4', '9078987978979'),
('1755201319', 'USR002', 'randal', 'Laki-Laki', 'Tidak Pinjam', '6', '9707908908908');

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `id_user` varchar(20) NOT NULL,
  `nama_user` varchar(25) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `akses` enum('admin','siswa') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`id_user`, `nama_user`, `username`, `password`, `akses`) VALUES
('USR001', 'admin', 'admin', 'admin123', 'admin'),
('USR002', 'randal', 'randal', 'randal123', 'siswa'),
('USR003', 'ling', 'ling', 'lingling123', 'siswa'),
('USR004', 'neva', 'neva', 'neva1234', 'siswa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`no_pinjam`),
  ADD KEY `id_siswa` (`NIS`),
  ADD KEY `id_buku` (`id_buku`),
  ADD KEY `id_siswa_2` (`NIS`),
  ADD KEY `id_buku_2` (`id_buku`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`no_kembali`),
  ADD KEY `no_pinjam` (`no_pinjam`),
  ADD KEY `id_siswa` (`NIS`),
  ADD KEY `id_buku` (`id_buku`),
  ADD KEY `id_siswa_2` (`NIS`),
  ADD KEY `id_buku_2` (`id_buku`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`NIS`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id_buku`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `peminjaman_ibfk_3` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD CONSTRAINT `pengembalian_ibfk_3` FOREIGN KEY (`id_buku`) REFERENCES `buku` (`id_buku`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengembalian_ibfk_4` FOREIGN KEY (`no_pinjam`) REFERENCES `peminjaman` (`no_pinjam`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pengembalian_ibfk_5` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
