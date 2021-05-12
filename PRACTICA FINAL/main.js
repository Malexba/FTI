const receta = new Vue({
    el: '#receta',
    data: {
        receta: {}
    },
    mounted(){
        //alert("mounted")
        const vm = this
        fetch("receta.json")
        .then(response => response.json())
        .then(data => vm.receta=data);
    }
})