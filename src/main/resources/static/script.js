const products = [
    { id: 1, name: 'Жаба-муля', price: 500 },
    { id: 2, name: 'Жаба-подорожник', price: 300 },
    { id: 3, name: 'Жаба-ватрушка', price: 450 }
];

let cart = [];

function renderProducts() {
    const productList = document.getElementById('product-list');
    productList.innerHTML = '';

    products.forEach(product => {
        const productDiv = document.createElement('div');
        productDiv.className = 'product';
        productDiv.innerHTML = `
            <h3>${product.name}</h3>
            <p>Цена: ${product.price} руб.</p>
            <button onclick="addToCart(${product.id})">Добавить в корзину</button>
        `;
        productList.appendChild(productDiv);
    });
}

function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    cart.push(product);
    updateCartCount();
    alert(`${product.name} добавлена в корзину!`);
}

function updateCartCount() {
    const cartCount = document.getElementById('cart-count');
    cartCount.innerText = cart.length;
}

document.getElementById('home').addEventListener('click', () => {
    document.getElementById('product-list').style.display = 'block';
    document.getElementById('cart-section').style.display = 'none';
});

document.getElementById('cart').addEventListener('click', () => {
    document.getElementById('product-list').style.display = 'none';
    document.getElementById('cart-section').style.display = 'block';

    const cartItems = document.getElementById('cart-items');
    cartItems.innerHTML = '';
    if (cart.length === 0) {
        cartItems.innerHTML = '<p>Корзина пуста</p>';
    } else {
        cart.forEach(item => {
            const div = document.createElement('div');
            div.innerHTML = `${item.name} - ${item.price} руб.`;
            cartItems.appendChild(div);
        });
    }
});

document.getElementById('checkout').addEventListener('click', () => {
    alert('Спасибо за покупку!');
    cart = [];
    updateCartCount();
    document.getElementById('cart-items').innerHTML = '<p>Корзина пуста</p>';
});

renderProducts();
