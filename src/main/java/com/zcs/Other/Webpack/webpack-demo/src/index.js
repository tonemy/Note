import {cube} from './main.js';
import printMe from './print.js';
import './style.css'
if (process.env.NODE_ENV !== 'production') {
       console.log('Looks like we are in development mode!');
}

function component() {
    var element = document.createElement('pre');
    var btn = document.createElement('button');


    btn.innerHTML = 'Click me and check the console!';
    btn.onclick = printMe;

    element.innerHTML = [
        'Hello webpack!',
        '5 cubed is equal to' + cube(5)
    ].join('\n\n');

    element.appendChild(btn);

    return element;
}
let element = component();
document.body.appendChild(element);

if(module.hot) {
    module.hot.accept('./print.js', function () {
        console.log('Acceptiong the updated printeMe module!');
        document.body.removeChild(element);
        element = component();//重新渲染
        document.body.appendChild(element);
    })
}