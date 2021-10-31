-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 31, 2021 lúc 02:10 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `trasua`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(1000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(7, 8, 1, 'Trà Sữa Ba Anh Em', 144000, 3),
(8, 8, 6, 'Trà Dứa Hồng Hạc', 80000, 2),
(9, 9, 1, 'Trà Sữa Ba Anh Em', 144000, 3),
(10, 9, 2, 'Trà Sữa Trân Châu Hoàng Gia', 184000, 4),
(11, 9, 6, 'Trà Dứa Hồng Hạc', 240000, 6),
(12, 10, 2, 'Trà Sữa Trân Châu Hoàng Gia', 276000, 6),
(13, 10, 1, 'Trà Sữa Ba Anh Em', 144000, 3),
(14, 10, 5, 'Hồng Long Pha Lê Tuyết', 88000, 2),
(15, 11, 2, 'Trà Sữa Trân Châu Hoàng Gia', 138000, 3),
(16, 11, 5, 'Hồng Long Pha Lê Tuyết', 88000, 2),
(17, 12, 4, 'Trà Sữa Khoai Môn Hoàng Kim', 46000, 1),
(18, 13, 1, 'Trà Sữa Ba Anh Em', 96000, 2),
(19, 15, 1, 'Trà Sữa Ba Anh Em', 48000, 1),
(20, 16, 1, 'Trà Sữa Ba Anh Em', 48000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `diachi` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `diachi`) VALUES
(8, 'Anh Duy', 123456789, 'Hutech'),
(9, 'Test', 123456789, 'hsuhsh'),
(10, 'Duy', 123456789, 'Test'),
(11, 'Phúc', 1576994578, 'abcjs'),
(12, 'Duy', 91354845, 'hajaush'),
(13, 'Duy', 164848, 'ahjajsh'),
(14, 'Duy', 3466154, 'Jdjdj'),
(15, 'Duy', 1618797945, 'jsjsjsj'),
(16, 'Duy', 338187657, 'jaushh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Trà sữa', 'https://brazel.tk/trasua/hinh/icon/trasua.png'),
(2, 'Fresh Fruit Tea', 'https://brazel.tk/trasua/hinh/icon/fruittea.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Trà Sữa Ba Anh Em', 48000, 'https://brazel.tk/trasua/hinh/sanpham/1578305659051_tra_sua_3_anh_em_e5929a819d2c4b08a1ae2caafe921473_grande.jpg', 'Vị nồng đặc trưng từ hồng trà sữa kết hợp với mùi sữa thơm ngậy. Đúng như tên gọi, trà sữa Ba anh em gồm 3 topping: trân châu giai dòn từ 100% tinh bột sắn, pudding, rau câu mềm, thanh, mát lạnh', 1),
(2, 'Trà Sữa Trân Châu Hoàng Gia', 46000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-10.jpg', 'Nhắc đến trà sữa, người ta thường nghĩ ngay đến trân châu đi kèm. Một trong những \" lão làng\", Trà sữa Trân châu Boba xuất hiện từ những ngày đầu khi Trà sữa du nhập vào Việt Nam. Đến nay, dù xuất hiện nhiều loại đồ uống khác, Trà sữa trân châu hoàng gia vẫn chiếm một vị trí nhất định trong lòng thực khách.', 1),
(3, 'Trà Sữa Kim Cương Đen Okinawa', 46000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-21.jpg', 'Trà Sữa Kim Cương Đen Okinawa', 1),
(4, 'Trà Sữa Khoai Môn Hoàng Kim', 46000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-8.jpg', 'Vị ngọt của sữa thêm vị chát đặc trưng của hồng trà, thêm trân châu dẻo dẻo và khoai môn béo bùi cho ra đời ly trà sữa khoai môn hoàng kim thơm ngon hấp dẫn ', 1),
(5, 'Trà dứa hồng hạc', 40000, 'https://brazel.tk/trasua/hinh/sanpham/tra-dua-hong-hac.jpg', 'Từ nguyên liệu là trái dứa với cách pha chế mới lạ, giúp cơ thể thanh mát, khỏe đẹp trong mùa hè nóng bức. Thêm vào đó là vị ngọt thanh chua chua ở đầu lưỡi mang lại sự pha trộn hài hòa giữa vị chua và ngọt, kết hợp với thạch băng tuyết ngọt ngào tạo nên cơn lốc thổi bay khoảng cách \"friendzone\".', 2),
(7, 'Probi Bưởi Trân Châu Sương Mai', 42000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-2.jpg', 'Bưởi Năm Roi miền Tây có chứa chất chống oxy hóa giúp da trẻ trung, mịn màng. Đặc biệt, loải quả này còn có tác dụng giải độc và làm chậm quá trình phát triển các vấn đề liên quan đến gan.', 2),
(8, 'Trà sữa Panda', 40000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-9.jpg', 'Bạn sẽ thấy được màu kem sữa kết hợp cùng những viên trân châu đen nhánh và trân châu sợi vàng óng ánh. Đặc biệt là có thể cảm nhận rõ vị lá trà nhài tươi ngon, nhẹ nhàng, thanh mát, lưu luyến nơi đầu lưỡi. Đây là thức uống đặc biệt phù hợp với những người thường xuyên phải lao động trí óc hay suy nghĩ nhiều.', 1),
(9, 'Tiger Sugar', 52000, 'https://brazel.tk/trasua/hinh/sanpham/ezgif.com-gif-maker-12.jpg', '“Kiệt tác” Tiger Sugar ra đời là sự hòa quyện khéo léo của sữa tươi thanh trùng, trân châu và vị ngọt của siro đường hổ. Khách hàng từng nhận định, sản phẩm không chỉ ngon về phần uống, mà còn ngon cả về phần nhìn.', 1),
(10, 'Trà Xanh Chanh Leo', 33000, 'https://brazel.tk/trasua/hinh/sanpham/tra_xanh_chanh_leo_f75be54e6d8e4e9397d7da9f5b1420fc_grande.jpg', 'Một ly trà xanh chanh leo mát lạnh, thoảng thoảng vị chát từ trà xen lẫn vị chua thật dịu từ chanh leo, nhâm nhi thêm rau câu dừa mềm, thanh thanh, ngon tuyệt cú mèo.', 2),
(11, 'Trà Xanh Xoài', 38000, 'https://brazel.tk/trasua/hinh/sanpham/tra_xanh_xoai_35d69664c31248faaf3eeade044035ba_grande.jpg', 'Trà xanh xoài - một sự kết hợp tinh tế giữa vị trà đậm với vị thơm ngọt của xoài. Tất cả hòa quyện và tạo nên một hương vị cực hấp dẫn kích thích vị giác.', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTaiKhoan` int(11) NOT NULL,
  `Hoten` varchar(150) DEFAULT NULL,
  `Email` varchar(150) DEFAULT NULL,
  `Matkhau` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTaiKhoan`, `Hoten`, `Email`, `Matkhau`) VALUES
(1, 'Duy', 'anhduy@gmail.com', '78f762dc14e909ac2e1336b797948898'),
(2, '', 'admin@gmail.com', '21232f297a57a5a743894a0e4a801fc3'),
(3, 'Test', 'test@gmail.com', '098f6bcd4621d373cade4e832627b4f6');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTaiKhoan`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTaiKhoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
