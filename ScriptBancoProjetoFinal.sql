create schema projetofinal;
create table endereco(
	idEndereco int auto_increment primary key,
	uf varchar(45),
	cidade varchar(45)
	
);

create table usuario(
	idUsuario int auto_increment primary key,
	nome varchar(45),
	senha varchar(45)
);

create table proprietario(
	idProprietario int auto_increment primary key,
	nome varchar(45)
);
create table acesso(
	idAcesso int auto_increment primary key,
	quantidadeAcesso int
);
create table raca(
	idRaca int auto_increment primary key,
	nome varchar(45)
);
create table funcionario(
	idFuncionario int auto_increment primary key,
	nome varchar(45),
	salario double
);

create table vacina(
	idVacina int auto_increment primary key,
	nome varchar(45)
);



create table causa(
	idCausa int auto_increment primary key,
	nome varchar(45)
);
create table racao(
	idRacao int auto_increment primary key,
	nome varchar(45)
);
create table tarefa(
	idTarefa int auto_increment primary key,
	descricao varchar(100),
	dataTarefa date
);


create table comprador(
	idComprador int auto_increment primary key,
	nome varchar(45),
	tipo varchar(30),
	cpfcnpj long,
	idEndereco int,
	telefone long,
	foreign key (idEndereco) references endereco(idEndereco),
	numero long
);
create table notaVenda(
	idNotaVenda int auto_increment primary key,
	dataNota date,
	valor double,
	formaPagamento varchar(30),
	idComprador int,
	foreign key(idComprador) references comprador(idComprador)
);




create table fornecedor(
	idFornecedor int auto_increment primary key,
	nome varchar(45),
	tipo varchar(30),
	cpfcnpj long,
	telefone long,
	idEndereco int,
	foreign key (idEndereco) references endereco(idEndereco)
);
create table notaCompra(
	idNota int auto_increment primary key,
	dataNota date,
	valor double,
	formaPagamento varchar(45),
	idFornecedor int,
	foreign key (idFornecedor) references fornecedor(idFornecedor)
);
create table compraMedicamento(
	idCompraMedicamento int auto_increment primary key,
	idVacina int,
	dataCompra date,
	idNota int,
	idFornecedor int,
	quantidade int,
	foreign key (idVacina) references vacina(idVacina),
	foreign key (idNota) references notacompra(idNota),
	foreign key (idFornecedor) references fornecedor(idFornecedor)
);
create table matriz(
	idMatriz int auto_increment primary key,
	mossa long,
	brinco long,
	idNota int,
	dataEntrada date,
	dataNascimento date,
	peso double,
	valor double,
	idRaca int,
	idFornecedor int,
	observacao varchar(100),
	statusAtual varchar(30),
	idade int,
	tetasDireitas int,
	tetasEsquerdas int,
	numeroCiclos int,
	foreign key (idNota) references notacompra(idNota),
	foreign key (idRaca) references raca(idRaca),
	foreign key (idFornecedor) references fornecedor(idFornecedor)
);
create table aborto(
	idAborto int auto_increment primary key,
	idMatriz int,
	dataAborto date,
	observacao varchar(100),
	foreign key (idMatriz) references matriz(idMatriz)
);
create table compraRacao(
	idCompraRacao int auto_increment primary key,
	idRacao int,
	idNota int,
	idFornecedor int,
	dataCompra date,
	quantidade int,
	foreign key (idRacao) references racao(idRacao),
	foreign key (idNota) references notacompra(idNota),
	foreign key (idFornecedor) references fornecedor(idFornecedor)
);


create table granja(
	idGranja int auto_increment primary key,
	idProprietario int,
	idEndereco int,
	foreign key (idProprietario) references proprietario(idProprietario),
	foreign key(idEndereco) references endereco(idEndereco)
);




create table lote(
	idLote int auto_increment primary key,
	quantidadeLeitao int,
	idade int,
    numero long,
	observacao varchar(100)
);

create table desmame(
	idDesmame int auto_increment primary key,
	idMatriz int,
	dataDesmame date,
	dataPrevista date,
	quantidade int,
	pesoTotal double,
	pesoMedio double,
	idLote int,
	observacaoDesmame varchar(100),
	observacaoLote varchar(100),
	foreign key (idMatriz) references matriz(idMatriz),
	foreign key (idLote) references lote(idLote)
);



create table lactacao(
	idLactacao int auto_increment primary key,
	idMatriz int,
	quantDoados int,
	quantRecebidos int,
	quantMortos int,
	quantAtual int,
	foreign key (idMatriz) references matriz(idMatriz)
);

create table macho(
	idMacho int auto_increment primary key,
	mossa long,
	brinco long,
	idNota int,
	dataEntrada date,
	dataNascimento date,
	peso double,
	valor double,
	idRaca int,
	idFornecedor int,
	observacao varchar(100),
	statusAtual varchar(30),
	idade int,
	tetasDireitas int,
	tetasEsquerdas int,
	tipoUtilizacao varchar(45),
	foreign key (idNota) references notacompra(idNota),
	foreign key (idRaca) references raca(idRaca),
	foreign key (idFornecedor) references fornecedor(idFornecedor)
);

create table morteLeitaoCreche(
	idMorteLeitaoCreche int auto_increment primary key,
	dataMorte date,
	idCausa int,
	idLote int,
	quantidade int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idLote) references lote(idLote)
);

create table morteLeitaoMaternidade(
	idMorteLeitaoMaternidade int auto_increment primary key,
	dataMorte date,
	idCausa int,
	idMatriz int,
	quantidade int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idMatriz) references matriz(idMatriz)
);

create table movimentacaoLeitao(
	idMovimentacaoLeitao int auto_increment primary key,
	idMatrizDoadora int,
	idMatrizReceptora int,
	dataMovimentacao date,
	quantidade int,
	foreign key (idMatrizDoadora) references matriz(idMatriz),
	foreign key (idMatrizReceptora) references matriz(idMatriz)
);



create table outrasSaidas(
	idOutrasSaidas int auto_increment primary key,
	idLote int,
	tipoSaida varchar(30),
	dataSaida date,
	quantidade int,
	pesoTotal double,
	foreign key(idLote) references lote(idLote)
);

create table parto(
	idParto int auto_increment primary key,
	idMatriz int,
	tipoParto varchar(45),
	dataParto Date,
	quantVivos int,
	quantMortos int,
	quantMumificados int,
	quantNatimortos int,
	pesoTotal double,
	pesoMedio double,
	idFuncionario int,
	foreign key (idMatriz) references matriz(idMatriz),
	foreign key (idFuncionario) references funcionario(idFuncionario)
);

create table repeticaoCio(
	idRepeticaoCio int auto_increment primary key,
	idMatriz int,
	dataRepeticao date,
	observacao varchar(100),
	foreign key (idMatriz) references matriz(idMatriz)
);

create table saidaracao(
	idSaidaRacao int auto_increment primary key,
	idRacao int,
	quantidade int,
	dataSaida date,
	destino varchar(45),
	foreign key (idRacao) references racao(idRacao)
);

create table semen(
	idSemen int auto_increment primary key,
	brinco long,
	dataEntrada date,
	dataNascimento date,
	idRaca int,
	idFornecedor int,
	idNota int,
	foreign key (idRaca) references raca(idRaca),
	foreign key (idFornecedor) references fornecedor(idFornecedor),
	foreign key (idNota) references notacompra(idNota)
);

create table transferenciaentrelotes(
	idTransferencia int auto_increment primary key,
	idLoteOrigem int,
	idLoteDestino int,
	quantidade int,
	dataTransferencia date,
	pesoTotal double,
	foreign key (idLoteOrigem) references lote(idLote),
	foreign key(idLoteDestino) references lote(idLote)
);

create table vendaleitao(
	idVendaLeitao int auto_increment primary key,
	dataVenda date,
	valor double,
	idComprador int,
	gta long,
	idNota int,
	pesoTotal double,
	pesoMedio double,
	idLote int,
	quantidade int,
	foreign key (idComprador) references comprador(idComprador),
	foreign key (idNota) references notavenda(idNotaVenda),
	foreign key (idLote) references lote(idLote)
);

create table cobertura(
	idCobertura int auto_increment primary key,
	idMatriz int,
	foreign key (idMatriz) references matriz(idMatriz)
);

create table montaMacho (
	idMontaMacho int auto_increment primary key,
	dataMonta date,
	idCobertura int,
	idMacho int,
	idFuncionario int,
	tipo varchar(45),
	foreign key (idCobertura) references cobertura(idCobertura),
	foreign key (idMacho) references macho(idMacho),
	foreign key (idFuncionario) references funcionario(idFuncionario)
);

create table montaSemen (
	idMontaSemen int auto_increment primary key,
	dataMonta date,
	idCobertura int,
	idSemen int,
	idFuncionario int,
	tipo varchar(45),
	foreign key (idCobertura) references cobertura(idCobertura),
	foreign key (idSemen) references semen(idSemen),
	foreign key (idFuncionario) references funcionario(idFuncionario)
);

create table descartemacho(
	idDescarte int auto_increment primary key,
	dataDescarte date,
	idCausa int,
	idMacho int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idMacho) references macho(idMacho)
);

create table descartematriz(
	idDescarte int auto_increment primary key,
	dataDescarte date,
	idCausa int,
	idMatriz int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idMatriz) references matriz(idMatriz)
);

create table morteMacho(
	idMorte int auto_increment primary key,
	dataMorte date,
	idCausa int,
	idMacho int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idMacho) references macho(idMacho)
);

create table morteMatriz(
	idMorte int auto_increment primary key,
	dataMorte date,
	idCausa int,
	idMatriz int,
	foreign key (idCausa) references causa(idCausa),
	foreign key (idMatriz) references matriz(idMatriz)
);

create table vacinacaomacho(
	idVacinacao int auto_increment primary key,
	idVacina int,
	dataVacinacao date,
	quantidadeUsada int,
	idMacho int,
	foreign key (idVacina)references vacina(idVacina),
	foreign key (idMacho)references macho(idMacho)
);

create table vendamatriz(
	idVenda int auto_increment primary key,
	dataVenda date,
	valor double,
	idComprador int,
	gta long,
	idNota int,
	pesoTotal double,
	pesoMedio double,
	idMatriz int,
	foreign key (idComprador) references comprador(idComprador),
	foreign key (idNota) references notavenda(idNotaVenda),
	foreign key (idMatriz) references matriz(idMatriz)
);

create table vacinacaomatriz(
	idVacinacao int auto_increment primary key,
	idVacina int,
	dataVacinacao date,
	quantidadeUsada int,
	idMatriz int,
	foreign key (idVacina)references vacina(idVacina),
	foreign key (idMatriz)references matriz(idMatriz)
);

create table vendamacho(
	idVenda int auto_increment primary key,
	dataVenda date,
	valor double,
	idComprador int,
	gta long,
	idNota int,
	pesoTotal double,
	pesoMedio double,
	idMacho int,
	foreign key (idComprador) references comprador(idComprador),
	foreign key (idNota) references notavenda(idNotaVenda),
	foreign key (idMacho) references macho(idMacho)
);

insert into acesso values(null,0);
