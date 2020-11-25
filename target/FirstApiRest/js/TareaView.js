class TareaView{

    constructor(tarea){
        this.tarea = tarea;
        this.onDeleteFinish = null;
        this.getAllTareas = null;
    }

    render = ()=>{
        let component = document.createElement('div');
        component.id = 'tarea'+this.tarea.id;
        component.className = 'tareaComponent';

        let fecha = document.createElement('small');
        fecha.className = 'fechaTarea';

        let descripcion = document.createElement('p');
        descripcion.className = 'descripcionTarea';
        
        let delButton = document.createElement('button');
        delButton.className = 'delBtn';

        fecha.innerHTML = new Date().toDateString(this.tarea.fecha);
        descripcion.innerHTML = this.tarea.descripcion;
        component.appendChild(fecha);
        component.appendChild(descripcion);
        component.appendChild(delButton);

        if(this.tarea.seccion === 1 || this.tarea.seccion === 2){
            let nextButton = document.createElement('button');
            nextButton.className = 'nextButton';
            nextButton.addEventListener('click', this.subirSeccion);
            component.appendChild(nextButton);
        }

        if(this.tarea.seccion === 2 || this.tarea.seccion === 3){
            let beforeButton = document.createElement('button');
            beforeButton.className = 'beforeButton';
            beforeButton.addEventListener('click', this.bajarSeccion);
            component.appendChild(beforeButton);
        }
        
        delButton.addEventListener('click', this.deleteTarea);
        return component;
    }

    deleteTarea = ()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                var response = JSON.parse(xhr.responseText);
                console.log(response);
                if(response.message === "Operacion exitosa"){
                    if(this.onDeleteFinish !== null){
                        this.onDeleteFinish();
                    }
                } else{
                    alert("No se pudo eliminar la tarea");
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/Parcial2/api/tarea/delete/'+this.tarea.id);
        xhr.send();
    }

    subirSeccion = ()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                var response = JSON.parse(xhr.responseText);
                console.log(response);
                if(response.message === "Operacion exitosa"){
                    this.getAllTareas();
                } else{
                    alert("No se pudo cambiar de seccion");
                }
            }
        });
        xhr.open('PUT', 'http://localhost:8080/Parcial2/api/tarea/cambiarSeccion?id='+this.tarea.id+'&seccion='+(this.tarea.seccion+1));
        xhr.send();
    }

    bajarSeccion = ()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                var response = JSON.parse(xhr.responseText);
                console.log(response);
                if(response.message === "Operacion exitosa"){
                    this.getAllTareas();
                } else{
                    alert("No se pudo cambiar de seccion");
                }
            }
        });
        xhr.open('PUT', 'http://localhost:8080/Parcial2/api/tarea/cambiarSeccion?id='+this.tarea.id+'&seccion='+(this.tarea.seccion-1));
        xhr.send();
    }
}