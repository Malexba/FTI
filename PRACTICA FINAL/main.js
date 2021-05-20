const recetario = new Vue({
    el: '#recetario',
    data: {
        recetario: {},
        mostrarReceta: false,
        receta: {}
    },
    methods: {
        muestroReceta: function (recetita) {
            this.receta = recetita
            this.mostrarReceta = true
        },
        retroceder: function () {
            this.mostrarReceta = false
        }
    },
    mounted(){
        //alert("mounted")
        const vm = this
        fetch("recetario.json")
        .then(response => response.json())
        .then(data => vm.recetario=data);
    }
})