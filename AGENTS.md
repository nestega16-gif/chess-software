# AGENTS.md — Desarrollo del Sistema de Ajedrez
Directrices para agentes en el desarrollo del proyecto de salas de ajedrez. El código debe mantener una separación clara entre frontend y backend.

## Stack y estructura
- Backend en Arquitectura Hexagonal: dominio y casos de uso en el centro; puertos y adaptadores para el exterior.
- Frontend con arquitectura ligera: UI separada, casos de uso, puerto de servicio y adaptador de comunicación.
- Contratos de comunicación explícitos mediante HTTP, WebSockets o similar.

## Convenciones
- Nombres descriptivos y código modular que respete la inyección de dependencias.
- Los commits deben mostrar un avance progresivo y lógico.

## Datos
- Modelo unificado y claro para identidades: usuarios, partidas, movimientos y estados.
- La persistencia queda estrictamente encapsulada en adaptadores de salida implementando puertos del dominio.

## Reglas de Arquitectura Críticas
- El frontend no escribe bajo ninguna circunstancia en la base de datos de forma directa.
- El backend es agnóstico a la UI y sus casos de uso no dependen de frameworks HTTP/Sockets.
- El modelo de la partida debe aislarse completamente de la capa de transporte.

## Forma de trabajar
- Diseña primero el modelo mínimo y los contratos antes de implementar la infraestructura.
- Al incorporar la "idea propia", asegúrate de que encaja en el alcance sin romper la arquitectura base.

## Límites
- ✅ Siempre: Valida las reglas de los movimientos en el dominio y encapsula los errores.
- ✅ Siempre: actualizar `MEMORY.md` al terminar cada tarea.
- ⚠️ Pregunta antes: Modificar los contratos de red entre frontend y backend.
- 🚫 Nunca: Subir credenciales, claves, dependencias compiladas (target, build, .jar) o rutas absolutas locales al repositorio.

## Verificación
- El backend y el frontend deben poder ejecutarse de manera independiente.
- Garantiza que el adaptador de comunicación del frontend sea sustituible por un mock.
- Verifica flujos completos: creación de sala, emparejamiento, persistencia de la partida y manejo de movimientos inválidos.

## Memoria
- Al empezar, lee `MEMORY.md` para conocer el estado del proyecto y las decisiones
  tomadas.
- Al terminar una tarea, actualízalo: estado actual, decisiones importantes (con su
  porqué) y errores a evitar.
- Mantenlo breve (máximo ~50 líneas): resume o elimina lo que ya no aporte.
- Si algo se convierte en una regla permanente, propón moverlo a `AGENTS.md` en lugar de
  dejarlo en la memoria.
- No guardes nunca datos sensibles (claves, tokens, datos personales). 