import { Component, OnInit, ViewChild } from "@angular/core";
import { MatTableDataSource } from '@angular/material/table';
import { ProductService } from "./../product.service";
import { Product } from "../product.model";
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: "app-product-read",
  templateUrl: "./product-read.component.html",
  styleUrls: ["./product-read.component.css"],
})

export class ProductReadComponent implements OnInit {

  products: Product[] = [];
  displayedColumns = ["id", "nome", "preco","quantidade", "action"];

  dataSource = new MatTableDataSource<Product>(this.products);

  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;

  isLoading = false;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.isLoading = true;
    this.productService.read().subscribe((products) => {
      this.products = products;
      this.dataSource.data = this.products;
      this.dataSource.paginator = this.paginator;
      this.isLoading = false;
    });
  }

  onPageChange(event: any): void {
    this.productService.setPage(event.pageIndex, event.pageSize);
    this.getProducts();
  }
}
