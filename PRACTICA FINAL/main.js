const recetario = new Vue({
    el: '#recetario',
    data: {
        recetario: {}
    },
    mounted(){
        //alert("mounted")
        const vm = this
        fetch("recetario.json")
        .then(response => response.json())
        .then(data => vm.recetario=data);
    }
})