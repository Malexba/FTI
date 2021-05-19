var app = new Vue({
    el: '#app',
    data: {
        brand: 'Vue Mastery',
        product: 'Calcetines',
        selectedVariant: 0,
        description: 'Un par de calcetines mulliditos y calentitos',
        details: [ "80% algodón", "20% poliéster", "Unisex" ],
        variants: [
            {
                variantId: 2234,
                variantColor: "green",
                variantImage: 'https://www.vuemastery.com/images/challenges/vmSocks-green-onWhite.jpg',
                variantQuantity: 8
            },
            {
                variantId: 2235,
                variantColor: "blue",
                variantImage: 'https://www.vuemastery.com/images/challenges/vmSocks-blue-onWhite.jpg',
                variantQuantity: 2
            }
        ],
        sizes: [ "S", "M", "L", "XL" ],
        cart: 0,
        link: 'https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=socks'
    },
    methods: {
        addToCart: function () {
            this.cart += 1
        },
        removeFromCart: function () {
            this.cart -= 1
        },
        updateProduct: function(index) {
            this.selectedVariant = index
            console.log(index)
        }
    },
    computed: {
        title() {
            return this.product + ' ' + this.brand
        },
        image() {
            return this.variants[this.selectedVariant].variantImage
        },
        onSale() {
            return this.variants[this.selectedVariant].variantQuantity
        }
    }
})