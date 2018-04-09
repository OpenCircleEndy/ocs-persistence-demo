create table subscription (
  id uuid not null,
  customerId uuid not null,
  productId uuid not null,
  validFrom timestamp WITH TIME ZONE,
  validTo timestamp WITH TIME ZONE
);
