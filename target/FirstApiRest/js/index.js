const descripcion = document.getElementById('descripcion');
const nuevaBtn = document.getElementById('nuevaBtn');
const toDoContainer = document.getElementById('toDoContainer');
const doingContainer = document.getElementById('doingContainer');
const doneContainer = document.getElementById('doneContainer');

const registrar = ()=>{
    let tareaObj = {
        id: 0,
        descripcion: descripcion.value,
        fecha: new Date().getTime(),
        seccion: 1
    };
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4){
            getAllTareas();
        }
    });
    xhr.open('POST', 'http://localhost:8080/Parcial2/api/tarea/nuevaTarea');
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.send(JSON.stringify(tareaObj));
    descripcion.value = '';
};

nuevaBtn.addEventListener('click', registrar);

const getAllTareas = ()=>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            toDoContainer.innerHTML = '<h3 class="tituloSeccion">To do</h3>';
            doingContainer.innerHTML = '<h3 class="tituloSeccion">Doing</h3>';
            doneContainer.innerHTML = '<h3 class="tituloSeccion">Done</h3>';
            for(let i = 0; i < response.length; i++){
                let tareaDTO = response[i];
                let view = new TareaView(tareaDTO);
                view.getAllTareas = ()=>{
                    getAllTareas();
                };
                view.onDeleteFinish = ()=>{
                    if(tareaDTO.seccion === 1){
                        toDoContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    } else if(tareaDTO.seccion === 2){
                        doingContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    } else {
                        doneContainer.removeChild(document.getElementById('tarea'+tareaDTO.id));
                    }
                };
                if(tareaDTO.seccion === 1){
                    toDoContainer.appendChild(view.render());
                } else if(tareaDTO.seccion === 2){
                    doingContainer.appendChild(view.render());
                } else {
                    doneContainer.appendChild(view.render());
                }
            }
        }
    });
    xhr.open('GET', 'http://localhost:8080/Parcial2/api/tarea/all');
    xhr.send();
}
getAllTareas();