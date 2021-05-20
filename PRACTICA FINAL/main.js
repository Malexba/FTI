const recetario = new Vue({
    el: '#receta',
    data: {
        receta: {}
    },
    mounted(){
        //alert("mounted")
        const vm = this
        fetch("recetario.json")
        .then(response => response.json())
        .then(data => vm.receta=data);
    }
})