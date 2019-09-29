import print from './print.js';
import './index.css';
import logo from './image/test.gif'
function component() {
    let element = document.createElement('div');
    let myLogo = new Image();
    myLogo.src = logo;
    element.appendChild(myLogo);
    return element;
}
document.write(print.print('tonemy'));
document.write("es语法解析测试结果 为:"+print.add(1, 2));
document.body.appendChild(component());



