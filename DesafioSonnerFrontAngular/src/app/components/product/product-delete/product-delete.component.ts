import { Component, OnInit } from "@angular/core";
import { Product } from "../product.model";
import { ProductService } from "./../product.service";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-product-delete",
  templateUrl: "./product-delete.component.html",
  styleUrls: ["./product-delete.component.css"],
})
export class ProductDeleteComponent implements OnInit {
  product!: Product;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get("id");
    if (id) {
      this.productService.readById(id).subscribe((product) => {
        this.product = product;
      });
    }
  }

  deleteProduct(): void {
    if (this.product && typeof this.product.id === 'number') {
      const confirmDelete = confirm("Tem certeza de que deseja excluir este produto?");
      if (confirmDelete) {
        this.productService.delete(this.product.id).subscribe(() => {
          this.productService.showMessage("Produto excluído com sucesso!");
          this.router.navigate(["/products"]);
        });
      }
    }
  }


  cancel(): void {
    this.router.navigate(["/products"]);
  }
}
