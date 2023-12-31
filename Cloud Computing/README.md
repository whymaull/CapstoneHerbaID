# Backend for HerbaID
**Powered by:**

<p style="text-align: center; background-color: #eee; display: inline-block; padding: 14px 20px; border-radius: 15px;">
<img src="https://upload.wikimedia.org/wikipedia/commons/5/51/Google_Cloud_logo.svg" width="250"/>
</p>

### Prerequisites
Before you begin, make sure you have the following software installed on your system:


- Node.js (version 18 or above)

- NPM (Node Package Manager)

## API Documentation

### Autentikasi Pengguna

#### Daftar Pengguna Baru

- **Endpoint**: `POST /api/auth/signup`
- **Description**: Allows users to register by providing information such as username, email, and password.
- **Headers**: -
- **Request Body**:
```json
{
  "username": "nama_pengguna",
  "email": "user@example.com",
  "password": "password_pengguna"
}
```

#### Masuk Pengguna

- **Endpoint**: `POST /api/auth/signin`
- **Description**: Allows users to log in using their email and password.
- **Headers**: -
- **Request Body**:
```json
{
  "email": "user@example.com",
  "password": "password_pengguna"
}
```
- **Response**:
```json
{
    "token": "token_otentikasi"
}
```

#### Keluar Pengguna

- **Endpoint**: `POST /api/auth/logout`
- **Description**: Logout from user session.
- **Authorization**: 
`Bearer token <token_otentikasi>`
- **Request Body**:
```json
{
  "email": "user@example.com",
  "password": "password_pengguna"
}
```
- **Response**:
```json
{
    "message": "Logout successful"
}
```

#### Detail Pengguna

- **Endpoint**: `GET /api/auth/user/id`
- **Description**: Returns user details based on the given user ID.
- **Authorization**: 
`Bearer token <token_otentikasi>`
- **URL Parameters**: 
`{id}: ID unik untuk resep yang ingin diambil.`

- **Response**:
```json
{
    "user": {
        "userId": "TuDELE64TuPdTkyN6blNn0NM6093",
        "username": "namauser14",
        "email": "contoh14@mail.com"
    }
}
```


### Keluhan dan Rekomendasi Resep Berdasarkan Keluhan

#### Tambah Keluhan & Rekomendasi Resep

- **Endpoint**: `POST /api/complaints`
- **Description**: Allows users to add complaints and get recipe recommendations based on the selected complaint.
- **Headers**: 
`Content-Type: application/json`
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Request Body**:
```json
{
  "complaintType": "pusing",
}
```
- **Response**:
```json
{
    "message": "Keluhan berhasil ditambahkan",
    "recommendedRecipes": []
}
```

#### Dapatkan Rekomendasi Resep

- **Endpoint**: `POST /api/complaints/recommended`
- **Description**: Get recipe recommendations based on the selected complaint.
- **Headers**: 
`Content-Type: application/json`
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Request Body**:
```json
{
  "complaintType": "pusing",
}
```
- **Response**:
```json
{
    "recommendedRecipes": []
}
```


### Resep

#### Dapatkan Semua Resep

- **Endpoint**: `GET /api/recipe`
- **Description**: Returns a list of all available recipes.
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Response**:
```json
{
  "recipes": [
    {
      "recipeId": "id_resep",
      "name": "nama_resep",
      "imageURL": "url_gambar_resep",
      "preparationTime": "waktu_persiapan",
      "ingredients": [
        {
          "name": "nama_bahan",
          "quantity": "jumlah",
          "unit": "satuan"
        },
      ],
      "instructions": ["langkah_1", "langkah_2", "..."],
      "favoriteCount": "jumlah_favorite"
    },
  ]
}
```

#### Dapatkan Resep Berdasarkan ID

- **Endpoint**: `GET /api/recipe/id`
- **Description**: Returns recipe details based on the given recipe ID.
- **Authorization**: 
`Bearer token <token_otentikasi>`
- **URL Parameters**: 
`{id}: ID unik untuk resep yang ingin diambil.`

- **Response**:
```json
{
    "recipeId": "xcxvrZVhD8OPyeJrYGVb",
    "name": "Sirup Thyme dan Madu",
    "instructions": [
        "Hancurkan daun thyme dan campur dengan madu.",
        "Ambil 1 sendok teh campuran tersebut sesuai kebutuhan untuk meredakan batuk."
    ],
    "preparationTime": "5 menit",
    "ingredients": [
        "1 sendok makan daun thyme segar",
        "2 sendok makan madu"
    ],
    "image": "https://storage.googleapis.com/image-recipe/Thyme%20and%20Honey%20Syrup.jpg",
    "complaintType": "Batuk"
}
```


### Resep Favorit Pengguna

#### Dapatkan Resep Favorit Pengguna

- **Endpoint**: `GET /api/user/favorites`
- **Description**: Returns a list of recipes saved as favorites by a specific user.
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Response**:
```json
{
    "favoriteRecipes": []
}
```

#### Tambah Resep ke Favorit Pengguna

- **Endpoint**: `POST /api/user/favorites/add`
- **Description**: Adds a recipe to a user's list of favorites.
- **Headers**: 
`Content-Type: application/json`
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Request Body**:
```json
{
  "recipeId": "id_resep"
}
```
- **Response**:
```json
{
    "message": "Recipe added to favorites successfully"
}
```

### Identifikasi Herbal

#### Identifikasi Herbal dari Gambar

- **Endpoint**: `POST /api/herbal/identify`
- **Description**: Allows users to upload an image to identify the herbal content and retrieve information about it.
- **Headers**: 
`Content-Type: multipart/form-data`
- **Authorization**: 
`Bearer token <token_otentikasi>`

- **Request Body**: Form Data
`herbalImage: Image file for herbal identification`

- **Response**:
```json
{
  "herbalData": {
    "herbalId": "id_herbal",
    "name": "nama_herbal",
    "imageURL": "url_gambar_herbal",
    "about": "informasi_tentang_herbal",
    "benefits": ["manfaat_1", "manfaat_2", "..."],
    "recipeIds": ["id_resep_1", "id_resep_2", "..."]
  },
  "recipes": [
    {
      "recipeId": "id_resep",
      "name": "nama_resep",
      "imageURL": "url_gambar_resep",
      "preparationTime": "waktu_persiapan",
      "ingredients": [
        {
          "name": "nama_bahan",
          "quantity": "jumlah",
          "unit": "satuan"
        }
      ],
      "instructions": ["langkah_1", "langkah_2", "..."],
      "favoriteCount": "jumlah_favorite"
    }
  ]
}
```

**Pastikan untuk mengganti `<token_otentikasi>` dengan token autentikasi yang sesuai saat melakukan request pada dokumentasi API.**
