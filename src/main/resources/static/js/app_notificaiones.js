const NotificacionAPI = (() => {
    const STORAGE_KEY = 'notificaciones';

    function obtenerTodas() {
        return JSON.parse(localStorage.getItem(STORAGE_KEY)) || [];
    }

    function agregar(mensaje) {
        const notificaciones = obtenerTodas();
        notificaciones.push(mensaje);
        localStorage.setItem(STORAGE_KEY, JSON.stringify(notificaciones));
    }

    function eliminar(index) {
        const notificaciones = obtenerTodas();
        if (index >= 0 && index < notificaciones.length) {
            notificaciones.splice(index, 1);
            localStorage.setItem(STORAGE_KEY, JSON.stringify(notificaciones));
        }
    }

    function vaciar() {
        localStorage.removeItem(STORAGE_KEY);
    }

    return {
        obtenerTodas,
        agregar,
        eliminar,
        vaciar
    };
})();