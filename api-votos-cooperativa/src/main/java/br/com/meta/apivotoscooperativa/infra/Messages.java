package br.com.meta.apivotoscooperativa.infra;

public interface Messages {
    final String PAUTA_INEXISTENTE = "Não existe pauta com o ID informado!";
    final String PAUTAS_INEXISTENTES = "Não existem pautas cadastradas!";
    final String SESSAO_INEXISTENTE = "Sessão não encontrada.";
    final String ASSOCIADO_INEXISTENTE = "Associado não encontrado.";
    final String ASSOCIADOS_INEXISTENTES = "Não há associados cadastrados!";
    final String VOTO_UNICO = "Associado ja votou nessa sessão.";
    final String EXISTE_SESSAO_PARA_PAUTA = "Já existe uma sessão para essa pauta!";
    final String CPF_NAO_ELEGIVEL = "Este CPF não é elegivel para voto!";
    final String CPF_JA_CADASTRADO = "CPf já cadastrado!";
    final String SESSAO_FECHADA = "Sessão não está aberta a votações!";
    final String SYNTAX_VOTO_INVALIDO = "O campo 'voto' deve ser 'sim','nao' ou 'não'.";
    final String PAUTA_DELETADA = "A pauta foi deletada com sucesso";
    final String ASSOCIADO_DELETADO = "Associado deletado com sucesso";
    final String CPF_INVALIDO = "CPF deve ser uma sequência de 11 caracteres numerico!";
}
