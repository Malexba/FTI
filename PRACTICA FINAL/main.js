var receta = new Vue({
    el: '#receta',
    data: {
    },
    mounted(){
        //alert("mounted")
        const vm = this
        fetch("receta.json")
        .then(response => response.json())
        .then(data => vm=data);
    }
})