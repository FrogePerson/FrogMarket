let cart = [];  
let products = []; // Глобальная переменная для хранения продуктов  

async function fetchProducts() {  
    try {  
        const response = await fetch('/Frogs');  
        if (!response.ok) {  
            throw new Error('Сеть не в порядке.');  
        }  
        products = await response.json();  
        renderProducts(products);  
    } catch (error) {  
        console.error('Ошибка:', error);  
    }  
}  

function renderProducts(products) {  
    const productList = document.getElementById('product-list');  
    productList.innerHTML = '';  

    products.forEach(product => {  
        const productDiv = document.createElement('div');  
        productDiv.className = 'product';  

        // Добавление изображения
        const imgElement = product.image 
            ? `<img src="data:image/jpeg;base64,${product.image}" alt="${product.name}" style="width:100px;height:auto;">`
            : '<p>Нет изображения</p>';
        
        productDiv.innerHTML = `  
            <h3>${product.name}</h3>  
            <p>Цена: ${product.cost} руб.</p>  
            ${imgElement}
            <button onclick="addToCart('${product.name}')">Добавить в корзину</button>  
        `;  
        productList.appendChild(productDiv);  
    });  
}  

function addToCart(productName) { 
    const product = products.find(p => p.name === productName); 
    if (product) { 
        cart.push(product); 
        updateCartCount(); 
        alert(`${product.name} добавлена в корзину!`); 
    } else { 
        alert('Жаба не найдена!'); 
    } 
} 

function updateCartCount() { 
    const cartCount = document.getElementById('cart-count'); 
    cartCount.innerText = cart.length; 
} 

function toggleCart() { 
    const cartModal = document.getElementById('cart-modal'); 
    if (cartModal.style.display === 'none' || cartModal.style.display === '') { 
        cartModal.style.display = 'block'; 
        displayCartContents(); 
    } else { 
        cartModal.style.display = 'none'; 
    } 
} 

function displayCartContents() { 
    const cartContents = document.getElementById('cart-contents'); 
    cartContents.innerHTML = ''; 

    if (cart.length === 0) { 
        cartContents.innerHTML = '<p>Корзина пуста.</p>'; 
    } else { 
        cart.forEach((item, index) => { 
            const itemDiv = document.createElement('div'); 
            itemDiv.innerHTML = `<p>${item.name}: ${item.cost} руб. <button onclick="removeFromCart(${index})">Удалить</button></p>`; 
            cartContents.appendChild(itemDiv); 
        }); 
    } 
} 

function removeFromCart(index) { 
    cart.splice(index, 1); // Удаляем элемент из корзины 
    updateCartCount(); 
    displayCartContents(); // Обновляем содержимое корзины 
}  

function toggleActionPanel() { 
    const actionPanel = document.getElementById('action-panel'); 
    if (actionPanel.style.display === 'none' || actionPanel.style.display === '') { 
        actionPanel.style.display = 'block'; 
        document.querySelector('.arrow').innerHTML = '&#8593;'; 
    } else { 
        actionPanel.style.display = 'none'; 
        document.querySelector('.arrow').innerHTML = '&#8595;'; 
    } 
}  

function toggleAddFrogForm() {
    const form = document.getElementById('add-frog-form');
    form.style.display = form.style.display === 'none' || form.style.display === '' ? 'block' : 'none';
}

function addFrog() { 
    document.getElementById('add-frog-form').style.display = 'block'; 
} 

async function saveFrog() { 
    const name = document.getElementById('frog-name').value; 
    const cost = document.getElementById('frog-cost').value; 
    const imageInput = document.getElementById('frog-image'); 

    if (!name || !cost || !imageInput.files.length) { 
        alert("Пожалуйста, заполните все поля."); 
        return; 
    } 

    // Создание объекта для отправки данных
    const frogData = {
        name: name,
        cost: cost,
        image: await toBase64(imageInput.files[0]) // Преобразование изображения в Base64
    };

    try { 
        const response = await fetch('/Frogs/saveFrog', { 
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(frogData) // Преобразование объекта в строку JSON
        }); 

        if (response.ok) { 
            alert("Жаба добавлена!");
            fetchProducts(); // Обновите список продуктов
            document.getElementById('add-frog-form').style.display = 'none'; // Скрыть форму
            document.getElementById('frog-name').value = ''; // Сбросьте поля формы
            document.getElementById('frog-cost').value = '';
            imageInput.value = ''; // Сброс загрузки файлов
        } else { 
            alert("Ошибка при добавлении жабы"); 
        } 
    } catch (error) { 
        console.error("Ошибка:", error); 
        alert("Произошла ошибка. Пожалуйста, попробуйте еще раз."); 
    } 
}

// Функция для преобразования файла изображения в формат Base64
function toBase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result.split(',')[1]); // Получаем только часть Base64 без 'data:image/jpeg;base64,'
        reader.onerror = error => reject(error);
    });
}

function removeFrog() { 
    const name = prompt("Введите имя жабы для удаления:"); 
    if (name) { 
        fetch(`/Frogs/DeleteFrog/${name}`, { 
            method: 'DELETE' 
        }) 
        .then(response => { 
            if (response.ok) { 
                alert("Жаба удалена!"); 
                fetchProducts(); // Обновляем список продуктов 
            } else { 
                alert("Ошибка при удалении жабы"); 
            } 
        }); 
    } 
}

function addParameter() {
    const columnName = prompt("Введите название параметра:");
    const columnType = prompt("Введите тип параметра:");

    if (columnName && columnType) {
        fetch(`/Frogs/addColumn/${columnName}/${columnType}`, {
            method: 'PUT'
        })
        .then(response => {
            if (response.ok) {
                alert("Параметр добавлен!");
            } else {
                alert("Ошибка при добавлении параметра");
            }
        });
    }
}

// Функция для удаления параметра
function removeParameter() {
    const columnName = prompt("Введите название параметра для удаления:");

    if (columnName) {
        fetch(`/Frogs/deleteColumn/${columnName}`, {
            method: 'PUT'
        })
        .then(response => {
            if (response.ok) {
                alert("Параметр удален!");
            } else {
                alert("Ошибка при удалении параметра");
            }
        });
    }
}

fetchProducts();
