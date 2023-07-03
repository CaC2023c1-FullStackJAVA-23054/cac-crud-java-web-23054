/*
 Código tomado y adaptado de:
 https://formcarry.com/blog/how-to-upload-files-as-base64/ 
 https://stackoverflow.com/questions/14672746/how-to-compress-an-image-via-javascript-in-the-browser
 https://morioh.com/p/872a8ce21d61
 */

const form = document.getElementById('formAgregarAlumno');
const fotoInput = document.getElementById('inputFoto');

let fotoCodif;
let isFileReady = true;

const handleChangeInputFile = async (event) => {

    isFileReady = false

    const foto = fotoInput.files[0];
    if (foto) {
        let fileName = foto.name.split('.')[0];
        let imagen = new Image();
        imagen.src = URL.createObjectURL(foto);
        imagen.onload = function () {
            let canvas = document.createElement('canvas');
            let MAX_WIDTH = 512;
            let MAX_HEIGHT = 512;

            let width = imagen.width;
            let height = imagen.height;

            if (width > height) {
                if (width > MAX_WIDTH) {
                    height = height * (MAX_WIDTH / width);
                    width = MAX_WIDTH;
                }
            } else {
                if (height > MAX_HEIGHT) {
                    width = width * (MAX_HEIGHT / height);
                    height = MAX_HEIGHT;
                }
            }
            canvas.width = width;
            canvas.height = height;
            let ctx = canvas.getContext('2d');
            ctx.drawImage(imagen, 0, 0, imagen.width, imagen.height, 0, 0, width, height);
            canvas.toBlob(function (blob) {
                let f2 = new File([blob], fileName + ".jpeg");
                const promesaFoto = new Promise((resolve, reject) => {
                    const reader = new FileReader();
                    reader.readAsBinaryString(f2);

                    reader.onload = function (event) {
                        console.info(blob.size);
                        fotoCodif = `data:${foto.type};base64,${btoa(event.target.result)}`
                        resolve();
                    };

                    reader.onerror = function () {
                        console.log("No se pudo leer el archivo");
                        reject();
                    };
                });
                promesaFoto
                        .then(() => {
                            isFileReady = true;
                        })
                        .catch((error) => {
                            console.log('Ocurrió un error')
                            console.log(error);
                        });
            }, 'image/jpeg', 0.8);
        }
    }
};

const handleForm = async (event) => {
    event.preventDefault();

    if (!isFileReady) {
        console.log('Se está procesando la foto');
        return;
    }
    document.getElementById("fotoBase64").value = fotoCodif || "";
    form.submit();
};

fotoInput.addEventListener('change', handleChangeInputFile);
form.addEventListener('submit', handleForm);
