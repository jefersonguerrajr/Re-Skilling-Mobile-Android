import java.util.Scanner

data class User(var nome: String)

var usuarios = mutableListOf<User>()

fun limpaConsole(){
    repeat(50) { println() }
}

fun exibeMenu(){
    print("========== Crud de usuarios ==========\n")
    print("Digite uma opção:\n")
    print("1 - Cadastrar novo usuário\n")
    print("2 - Listar todos os usuários\n")
    print("3 - Pesquisar usuário\n")
    print("4 - Alterar usuário\n")
    print("5 - Remover usuário\n")
    print("6 - Finalizar\n")
}

fun cadastrarUsuario(){
    limpaConsole()
    while(true){
        println("Digite o nome do usuário:")
        val nome = Scanner(System.`in`).next()
        if(nome.isNullOrBlank()){
            println("Nome inválido, tente novamente.")
            continue
        }
        usuarios.add(User(nome))
        println("Usuario adicionado com sucesso!")
        break
    }
}

fun listarUsuarios(){
    limpaConsole()
    println("Listando usuários:")
    usuarios.forEach { user ->
        println(user.nome)
    }
}

fun pesquisarUsuario(){
    limpaConsole()
    while(true){
        println("Digite o nome do usuário a ser pesquisado:")
        val nome = Scanner(System.`in`).next()
        if(nome.isNullOrBlank()){
            continue
        }
        val usuarioSelecionado = usuarios.find { usuario -> usuario.nome.lowercase().contains(nome.lowercase())} ?: "Usuario não encontrado!"
        println(usuarioSelecionado)
        break
    }
}

fun alterarUsuario() {
    limpaConsole()
    while(true){
        println("Digite o nome do usuário a ser alterado:")
        val nome = Scanner(System.`in`).next()
        if(nome.isNullOrBlank()){
            continue
        }
        val usuarioSelecionado = usuarios.find { usuario -> usuario.nome.lowercase().contains(nome.lowercase())}
        if(usuarioSelecionado != null){
            println("Usuário encontrado: ${usuarioSelecionado.nome}")
            println("Digite o novo nome do usuário:")
            val novoNome = Scanner(System.`in`).next()
            if(novoNome.isNullOrBlank()){
                println("Nome inválido, tente novamente.")
                continue
            }
            usuarioSelecionado.nome = novoNome.lowercase()
            println("Usuário alterado com sucesso!")
            break
        } else{
            println("Usuario não encontrado!")
            break
        }
    }
}

fun removerUsuario(){
    limpaConsole()
    while(true){
        println("Digite o nome do usuario:")
        val nome = Scanner(System.`in`).next()
        if(nome.isNullOrBlank()){
            continue
        }
        usuarios.removeIf { usuarios -> usuarios.nome.lowercase().contains(nome.lowercase()) }
        println("Usuario removido com sucesso!")
        break
    }
}

fun main() {
    while (true) {
        exibeMenu()
        val input = Scanner(System.`in`)
        when (input.nextInt()) {
            1 -> cadastrarUsuario()
            2 -> listarUsuarios()
            3 -> pesquisarUsuario()
            4 -> alterarUsuario()
            5 -> removerUsuario()
            6 -> {
                println("Finalizando...")
                break
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}
