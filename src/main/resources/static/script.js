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

// Загрузка продуктов при инициализации
fetchProducts();