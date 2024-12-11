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
        productDiv.innerHTML = ` 
            <h3>${product.name}</h3> 
            <p>Цена: ${product.cost} руб.</p> 
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

// Функции для управления панелью действий
function toggleActionPanel() {
    const actionPanel = document.getElementById('action-panel');
    if (actionPanel.style.display === 'none' || actionPanel.style.display === '') {
        actionPanel.style.display = 'block';
        document.querySelector('.arrow').innerHTML = '&#8593;'; // Стрелка вверх
    } else {
        actionPanel.style.display = 'none';
        document.querySelector('.arrow').innerHTML = '&#8595;'; // Стрелка вниз
    }
}


// Функция для добавления жабы
function addFrog() {
    const name = prompt("Введите имя жабы:");
    const cost = prompt("Введите цену жабы:");
    const id = Math.floor(Math.random() * 1000);

    if (name && cost) {
        const frogData = { id, name, cost: parseInt(cost) };
        fetch('/Frogs/saveFrog', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(frogData)
        })
        .then(response => {
            if (response.ok) {
                alert("Жаба добавлена!");
                fetchProducts(); // Обновляем список продуктов
            } else {
                alert("Ошибка при добавлении жабы");
            }
        });
    }
}

// Функция для удаления жабы
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

// Функция для добавления параметра
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

// Загрузка продуктов при инициализации 
fetchProducts();
