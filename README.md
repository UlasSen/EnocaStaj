E-Ticaret Uygulaması
Bu proje, bir e-ticaret uygulamasının temel işlevlerini gerçekleştiren bir Spring Boot projesidir. 
Uygulama, ürünler, müşteriler, sepetler ve siparişler arasında çeşitli ilişkileri yönetir. Müşteriler sepetlerine ürün ekleyebilir ve bu ürünlerle sipariş oluşturabilirler. 
Siparişlerin ve sepetlerin toplam fiyatları her işlemde hesaplanır. Sipariş geçildikten sonra ürünlerin fiyatları güncellense bile, müşteri geçmiş fiyatları görebilir.

İçindekiler
-Özellikler
-Kullanılan Teknolojiler
-API Kullanımı
-Veritabanı Modeli

Özellikler
-Müşteri ekleme ve listeleme
-Ürün ekleme, güncelleme, silme ve listeleme
-Sepet oluşturma, güncelleme ve boşaltma
-Sepete ürün ekleme ve çıkarma
-Sipariş oluşturma ve listeleme
-Geçmiş siparişlerin detaylarını görüntüleme
-Ürün stok takibi

Kullanılan Teknolojiler
-Java
-Spring Boot
-Spring Data JPA
-H2 Database (veya başka bir veritabanı)
-lombok

Veritabanı Modeli
-Entity İlişkileri
Customer:
  Bir müşteri birden fazla siparişe sahip olabilir.
  Her müşteri bir sepete sahip olabilir.
Product:
  Ürünler sepetlerde ve siparişlerde yer alabilir.
Cart:
  Sepet bir müşteriyle ilişkilidir.
  Sepet birden fazla CartItem içerir.
CartItem:
  CartItem bir sepete ve bir ürüne aittir.
Order:
  Sipariş bir müşteriyle ilişkilidir.
  Sipariş birden fazla OrderItem içerir.
OrderItem:
  OrderItem bir siparişe ve bir ürüne aittir.
  Sipariş anındaki ürün fiyatı saklanır.
