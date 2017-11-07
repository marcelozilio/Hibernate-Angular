/* global self, $scope */
angular.module("Pessoa", [])
        .value('urlBase', 'http://localhost:8080/HibernateAngular/webresources/pessoa/')
        .controller("PessoaController", function ($http, urlBase, $scope) {
            var self = this;
            $scope.pessoas = [];
            self.pessoa = {};

            self.salvar = function () {
                $http({
                    method: 'POST',
                    url: urlBase + 'salvar',
                    data: self.pessoa
                }).then(function succesCallBack(response) {
                    self.atualizarTabela();
                    self.limparCampos();
                    $scope.formPes.$setPristine();
                }, function errorCallBack(response) {
                    alert("Erro ao tentar salvar");
                });
            };

            self.listar = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'listar'
                }).then(function succesCallBack(response) {
                    $scope.pessoas = response.data;
                }, function errorCallBack(response) {
                    alert("Erro ao listar");
                });
            };

            self.alterar = function (pessoa) {
                window.sessionStorage.setItem('pesAlt', JSON.stringify(pessoa));
                window.location.href = 'AlterarPessoa.html';
            };

            self.deletar = function (pessoa) {
                $http({
                    method: 'DELETE',
                    url: urlBase + 'deletar/' + pessoa.cod
                }).then(function succesCallBack(response) {
                    self.atualizarTabela();
                }, function errorCallBack(response) {
                    alert("Erro ao deletar");
                });
            };

            self.limparCampos = function () {              
                document.getElementById("nome").value = "";
                document.getElementById("idade").value = "";
            };

            self.atualizarTabela = function () {
                self.listar();
            };

            self.atualizarTabela();
        })
        .controller("AltPessoaController", function ($http, urlBase, $scope) {
            var self = this;
            self.pessoa = {};

            self.salvar = function () {
                $http({
                    method: 'POST',
                    url: urlBase + 'salvar',
                    data: self.pessoa
                }).then(function succesCallBack(response) {                   
                    window.sessionStorage.removeItem('pesAlt');
                    window.location.href = 'index.html';
                }, function errorCallBack(response) {
                    alert("Erro ao tentar salvar");
                });
            };

            self.active = function () {
                window.sessionStorage.setItem('teste', 'fudeu');
                
                self.pessoa = JSON.parse(window.sessionStorage.getItem('pesAlt'));

                if (self.pessoa !== null) {
                    document.getElementById("cod").value = self.pessoa.cod;
                    document.getElementById("nome").value = self.pessoa.nome;
                    document.getElementById("idade").value = self.pessoa.idade;
                } else {
                    window.location.href = 'index.html';
                }
            };
            
            self.active();
        });