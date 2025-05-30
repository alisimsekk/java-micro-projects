# QueryDSL Examples

Bu proje, Spring Boot ve QueryDSL kullanarak veritabanı sorgularını daha güvenli ve tip güvenli bir şekilde oluşturmayı gösteren bir örnek uygulamadır.

## Proje Yapısı

Proje, katmanlı mimariye uygun olarak aşağıdaki yapıda tasarlanmıştır:

```
src/main/java/com/alisimsek/querydslexamples/
├── config/                  # Konfigürasyon sınıfları
├── controller/              # REST API kontrolörleri
├── entity/                  # JPA entity sınıfları
├── dto/                     # Data Transfer Object sınıfları
├── repository/              # Repository sınıfları
└── service/                 # Servis sınıfları
```

## Entities

Projede iki ana entity bulunmaktadır:

1. **Category**: Ürün kategorilerini temsil eder.
2. **Product**: Ürünleri temsil eder, Category ile many-to-one ilişkisi vardır.

## Seçilen QueryDSL Örnekleri

Bu projede, QueryDSL'in dört temel örneği gösterilmiştir:

### 1. Constructor Projection
Constructor parametreleri ile eşleşecek şekilde DTO nesnelerini oluşturmak için kullanılır:

```java
queryFactory
    .select(Projections.constructor(ProductDto.class,
        product.id,
        product.name,
        product.description,
        product.price,
        product.stock,
            Projections.constructor(CategoryDto.class,
            category.id,
            category.name,
            category.description
            )))
    .from(product)
    .join(product.category, category)
    .fetch();
```

### 2. Aggregation İşlemleri
Kategorilere göre istatistik verilerini hesaplama:

```java
queryFactory
    .select(Projections.constructor(CategorySummaryDto.class,
        category.id,
        category.name,
        category.description,
        product.count(),
        product.price.min(),
        product.price.max(),
        product.price.avg(),
        product.stock.sum(),
        ))
    .from(category)
    .leftJoin(product).on(product.category.eq(category))
    .groupBy(category.id, category.name, category.description)
    .orderBy(product.count().desc())
    .fetch();
```

### 3. Case Expressions
Koşullu ifadelerin kullanımı - fiyata göre ürün sınıflandırması:

```java
queryFactory
    .select(Projections.constructor(ProductSummaryDto.class,
        product.id,
        product.name,
        product.price,
        category.name,
        new CaseBuilder()
            .when(product.price.lt(BigDecimal.valueOf(50))).then("Budget")
            .when(product.price.lt(BigDecimal.valueOf(200))).then("Mid-range")
            .when(product.price.lt(BigDecimal.valueOf(500))).then("Premium")
            .otherwise("Luxury"),
        product.stock,
        product.stock.lt(10)))
    .from(product)
    .join(product.category, category)
    .orderBy(product.price.asc())
    .fetch();
```

## QueryDSL Temel Özellikleri

QueryDSL'in temel özelliklerinden bazıları:

1. **Tip Güvenli Sorgular**: Derleme zamanında kontrol edilen sorgular
2. **Projection**: DTO nesnelerine doğrudan sorgu sonuçlarını dönüştürme
3. **Nestable Operations**: İç içe geçmiş sorgular ve alt sorgular
4. **Join İşlemleri**: İlişkili entityler üzerinde join işlemleri
5. **Koşullu İfadeler**: Case/When yapıları ile koşullu veri oluşturma
6. **Aggregation**: Grup bazlı hesaplamalar yapma (count, sum, avg, min, max)

## Başlangıç

H2 veritabanı konsolu:

```
http://localhost:9091/h2-console
```
