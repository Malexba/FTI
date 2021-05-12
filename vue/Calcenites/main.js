var app = new Vue({
    el: '#app',
    data: {
        product: 'Calcetines',
        description: 'Un par de calcetines mulliditos y calentitos',
        inventory: 8,
        onSale: true,
        details: [ "80% algodón", "20% poliéster", "Unisex" ],
        variants: [
            {
                variantId: 2234,
                variantColor: "verde"
            },
            {
                variantId: 2235,
                variantColor: "azul"
            }
        ],
        sizes: [ "S", "M", "L", "XL" ],
        image: 'https://www.vuemastery.com/images/challenges/vmSocks-green-onWhite.jpg',
        link: 'https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=socks'
    }
})