export interface Produto {
  id: number;
  nome: string;
  preco: number | null;
  categoria: { id: number; nome: string } | null;
  quantidade: number | null;
}
